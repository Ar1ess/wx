package com.softlab.wx.web.api;

import com.softlab.wx.common.RestData;
import com.softlab.wx.common.WxException;
import com.softlab.wx.common.util.JsonUtil;
import com.softlab.wx.core.model.vo.Comment;
import com.softlab.wx.core.model.vo.Community;
import com.softlab.wx.service.CommunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class CommunityController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CommunityService communityService;
    private final WxStepController wxStepController = null;
    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    String oppidA = wxStepController.oppidA;

    @RequestMapping(value = "/detail/{systemId}", method = RequestMethod.GET)
    public RestData getCommunityDetailByCommunitySystemId(@PathVariable(value = "systemId") Integer systemId, HttpServletRequest request) {
        logger.info("getCommunityDetailBySystemId : ");
        try {
            Community community = new Community();
            community.setSystemId(systemId);
            List<Map<String, Object>> al = new ArrayList<>();
            al.add(communityService.getCommunityDetailByCommunitySystemId(community));
            List<Map<String, Object>> datas = communityService. getAllComment(community);
            return new RestData(al, datas);
        } catch (WxException e) {
            return new RestData(1, e.getMessage());
        }
    }



    @RequestMapping(value = "/community", method = RequestMethod.GET)
    public RestData getAllCommunity( HttpServletRequest request) {
        logger.info("getAllCommunity : ");
        try {
            List<Map<String, Object>> data = communityService. getAllCommunity();

            return new RestData(data);
        } catch (WxException e) {
            return new RestData(1, e.getMessage());
        }
    }

    @RequestMapping(value = "/community/search/{keyword}", method = RequestMethod.GET)
    public RestData getCommunityByKeyword(@PathVariable(value = "keyword") String keyword, HttpServletRequest request){
        logger.info("getCommunityByKeyword : " + JsonUtil.getJsonString(keyword));
        try {
            List<Map<String, Object>> data = communityService.getCommunityByKeyword(keyword);

            return new RestData(data);
        } catch (WxException e) {
            return new RestData(1, e.getMessage());
        }
    }



    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public RestData insertCommunity (@RequestParam(value = "files") MultipartFile file1, @RequestParam(value = "files") MultipartFile file2, @RequestParam(value = "files") MultipartFile file3, Community community, HttpServletRequest request, HttpServletResponse response){
        logger.info(" insertCommunity: " + JsonUtil.getJsonString(community));
        try {
            boolean flag = communityService.insertCommunity(community, file1, file2, file3, oppidA);
            return new RestData(flag);
        } catch (WxException e) {
            return new RestData(5,e.getMessage());
        }
    }

    @RequestMapping(value = "/detail/{systemId}/comment", method = RequestMethod.POST)
    public RestData insertCommunityComment(@PathVariable(value = "systemId") Integer systemId, @RequestBody Comment comment, Community community, HttpServletRequest request){
        logger.info(" insertComment: " + JsonUtil.getJsonString(comment));
        logger.info(" insertCommunity: " + JsonUtil.getJsonString(community));
        try {
            boolean flag = communityService.insertCommunityComment(comment, systemId);
            return new RestData(flag);
        } catch (WxException e) {
            return new RestData(5,e.getMessage());
        }
    }


    @RequestMapping(value = "/my/{oppid}", method = RequestMethod.GET)
    public RestData selectMyCommunity(@PathVariable(value = "oppid") String oppid,  HttpServletRequest request) throws WxException {
        logger.info("selectMyCommunity: " + JsonUtil.getJsonString(oppid));

        try {
            List <Map<String, Object>> data = communityService.selectAllCommunityByWriter(oppid);
            return new RestData(data);
        } catch (WxException e) {
            return new RestData(1, e.getMessage());
        }

    }


    @RequestMapping(value = "/my/delete/{systemId}", method = RequestMethod.POST)
    public RestData deleteMyCommunity(@PathVariable(value = "systemId") Integer systemId, HttpServletRequest request) throws WxException{
        logger.info("deleteMyCommunity: " + JsonUtil.getJsonString(systemId));
        try {
            Community community = new Community();
            community.setSystemId(systemId);
            boolean flag = communityService.deleteCommunityBySystemId(community);
            return new RestData(flag);
        } catch (WxException e) {
            return new RestData(5,e.getMessage());
        }
    }


}

/**
 * 图片上传分析：
 * 1.一般情况下都是将用户上传的图片放到服务器的某个文件夹中，然后将图片在服务器中的路径存入数据库。
 *
 * 2.由于用户自己保存的图片文件名可能跟其他用户同名造成冲突，选择使用UUID来生成随机的文件名解决冲突。
 *
 * 3.具体依赖，pom相关依赖，主要是FreeMarker相关依赖为了展现页面，只是为了展示页面，这个不重要
 *
 * 4.application.properties相关配置
 *   除了视图模板相关的配置，重点是要配置一下文件上传的内存大小和文件上传路径
 */



