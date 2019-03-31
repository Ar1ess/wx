package com.softlab.wx.web.api;

import com.softlab.wx.common.RestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by LiXiwen on 2019/3/28 13:45.
 **/

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class VersionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/version",method = RequestMethod.GET)
    public RestData VersionControll(){
        HashMap<String,String> hashMap=new HashMap<>();
        String version="V1.0.0";
        String data="东北林业大学官方体验版发布，五大功能全部上线，可以正常使用";
        hashMap.put("version",version);
        hashMap.put("data",data);
        return new RestData(hashMap);
    }


}
