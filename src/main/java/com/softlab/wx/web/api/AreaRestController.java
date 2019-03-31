package com.softlab.wx.web.api;

import com.softlab.wx.common.ErrorMessage;
import com.softlab.wx.common.RestData;
import com.softlab.wx.common.util.JsonUtil;
import com.softlab.wx.core.model.vo.Area;
import com.softlab.wx.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * Created by LiXiwen on 2019/3/10.
 *
 **/

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class AreaRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AreaService areaService;

//    @RequestMapping(value = "/areaId", method = RequestMethod.GET)
//    public Area selectByAreaId(@RequestParam(value = "areaId", required = false) Integer areaId) {
//        logger.info("报修开始查询...");
//        return areaService.selectAreaById(areaId);
//    }

    @RequestMapping(value = "/areaId", method = RequestMethod.GET)
    public int selectByAreaId(@RequestParam(value = "areaId", required = false) Integer areaId) {
        logger.info("报修开始查询...");
        return 123;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)

    public RestData addUser(@RequestBody Area area , HttpServletRequest request) {
        logger.info("报修开始新增 : " + JsonUtil.getJsonString(area));
        //打印看下值
        System.out.println(area.getAreaLocation());

        boolean flag = areaService.addArea(area);
        if(flag){
            return new RestData(area.getAreaId());
        }else{
            return new RestData(1, ErrorMessage.POST_AREA_FAILED);
        }
    }

}
