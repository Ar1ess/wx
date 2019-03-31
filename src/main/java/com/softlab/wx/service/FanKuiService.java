package com.softlab.wx.service;

import com.softlab.wx.core.model.vo.FanKui;
/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/
public interface FanKuiService {
    /**
     * 添加反馈数据
     * @param fanKui
     * @return
     */
    boolean addFanKui(FanKui fanKui);
}
