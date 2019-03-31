package com.softlab.wx.service;

import com.softlab.wx.core.model.vo.Bushu;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/

public interface WxStepDecryptService {

    boolean addData(Bushu bushu, String userInfo);

    List<Map<String, Object>> selectAllPaceByOrder(String oppid);

}
