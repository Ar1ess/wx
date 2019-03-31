package com.softlab.wx.web.api;

import com.google.gson.Gson;
import com.softlab.wx.common.ErrorMessage;
import com.softlab.wx.common.RestData;
import com.softlab.wx.common.util.AES;
import com.softlab.wx.common.util.HttpRequestor;
import com.softlab.wx.core.mapper.WxStepDecryptMapper;
import com.softlab.wx.core.model.vo.Bushu;
import com.softlab.wx.core.model.vo.Pace;
import com.softlab.wx.service.WxStepDecryptService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.*;

/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class WxStepController {

    private static final Logger logger = LoggerFactory.getLogger(WxStepController.class);

    private final WxStepDecryptService wxStepDecryptService;
    private final WxStepDecryptMapper wxStepDecryptMapper;
    @Autowired
    public WxStepController(WxStepDecryptService wxStepDecryptService, WxStepDecryptMapper wxStepDecryptMapper){
        this.wxStepDecryptService=wxStepDecryptService;
        this.wxStepDecryptMapper=wxStepDecryptMapper;
    }


    private String appid = "wxa521896709798f9a";
    private String secret = "8d725528750b12e2ca6bb87fa665723a";
    private String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=";
    private String url0 = "&grant_type=authorization_code";
    public static String oppidA;


    /**
     * 接收code，返回oppnid 和 sessionkey  进行下一步的获取步数
     * @param code
     * @return
     */
    @ApiOperation(value = "微信登录")
    @ApiImplicitParam(name = "code",value = "微信code",required = true, dataType = "int")     //两行为swagger的注解，可以不要
    @RequestMapping("/onlogin")
    //@RequestMapping(value = "/onlogin/{code}", method = RequestMethod.GET)
    public String login(String code) {
        logger.info("code : "+code);
        String oppid = "";
        String newOppid="";
        JSONObject oppidObj = null;
        try {
            oppid = new HttpRequestor().doGet(url + code + url0);    //url+code+url0
            logger.info("doGet Over");
            oppidObj = JSONObject.fromObject(oppid);
            System.out.println(oppidObj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        newOppid="["+oppid.replace('\"', '\'')+"]";
        logger.info("获取到的json串" + newOppid);

        JSONArray json = JSONArray.fromObject(newOppid); // 首先把字符串转成 JSONArray  对象
        logger.info("json.size:="+json.size());
        if(json.size()>0){
                JSONObject job = json.getJSONObject(0);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                oppidA=(String)job.get("openid");  // 得到 每个对象中的属性值
        }
        Gson gson = new Gson();
        return gson.toJson(oppidObj);
    }



    @RequestMapping(value="/decrypt" ,method = RequestMethod.POST)
    public RestData decrypt(@RequestBody Bushu bushu){

        HashMap<String,String> hashMap=new LinkedHashMap<>();
        try {
            byte[] resultByte  = AES.decrypt(Base64.decodeBase64(bushu.getEncryptedData()),
                    Base64.decodeBase64(bushu.getSessionKey()),
                    Base64.decodeBase64(bushu.getIv()));
            if(null != resultByte && resultByte.length > 0){
                String userInfo = new String(resultByte, "UTF-8");
                bushu.setUserId(oppidA);
                logger.info(resultByte.length+"lengtb");
                logger.info(userInfo);
                /**
                 * 解析 此用户3天的步数，在serviceImpl中判断，
                 * 步数不存在，添加步数，
                 * 如果步数存在并且相等，则只显示，不添加，不更新，
                 * 如果步数存在不相等，更新步数。
                 * 均在  addData 中完成
                 *
                 */
                logger.info("oppidA: "+bushu.getUserId()+"days: "+bushu.getDays()+"username: "+bushu.getUserName()+"EncryptedData"+bushu.getEncryptedData());
                System.out.println("addData:"+wxStepDecryptService.addData(bushu,userInfo));
                /*if(wxStepDecryptService.addData(bushu,userInfo)){
                   hashMap.put("添加或更新用户信息","success");
               }else{
                   hashMap.put("添加或更新用户信息","failed");
               }*/
            }else{
                return new RestData(1, ErrorMessage.JIE_MI);
            }
        }catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        hashMap.put("解密情况","success");
        return new RestData(hashMap);
    }


    @RequestMapping(value = "/selectPace", method = RequestMethod.GET)
    public RestData selectPaceDetail(){

        Map<String,Object> linkedHashMap=new HashMap<>(8);

        Pace myPace = wxStepDecryptMapper.selectMyPaceInNew(oppidA);
        Map<String, Object> myLinkedHashMap=new HashMap<>(8);
        myLinkedHashMap.put("Irank",myPace.getUserRank());
        myLinkedHashMap.put("Iicon",myPace.getUserIcon());
        myLinkedHashMap.put("Iname",myPace.getUserName());
        myLinkedHashMap.put("Ipaiwei",myPace.getUserPaiwei());
        myLinkedHashMap.put("Ipace",myPace.getUserPace());

        System.out.println(myPace.getUserPace());

        myLinkedHashMap.put("Iimg",myPace.getUserImg());
        myLinkedHashMap.put("Istyle",myPace.getUserColor());

        System.out.println(myPace.getUserName());

        List<Map<String,Object>> allPaceList = wxStepDecryptService.selectAllPaceByOrder(oppidA);
        System.out.println(allPaceList.size()+myPace.getUserName());
        return new RestData(myLinkedHashMap,allPaceList);
    }


}
