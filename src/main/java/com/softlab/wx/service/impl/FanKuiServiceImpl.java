package com.softlab.wx.service.impl;

import com.softlab.wx.core.mapper.FanKuiMapper;
import com.softlab.wx.core.model.vo.FanKui;
import com.softlab.wx.service.FanKuiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/

@Service
public class FanKuiServiceImpl implements FanKuiService {

    @Autowired
    private FanKuiMapper fanKuiMapper;
    /**
     * 添加反馈数据
     * @param fanKui
     * @return
     */
    @Override
    public boolean addFanKui(FanKui fanKui){
        boolean flag=false;
        try{
            fanKuiMapper.addFanKui(fanKui);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

}
