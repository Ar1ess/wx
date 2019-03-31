package com.softlab.wx.service;

import com.softlab.wx.core.model.vo.Time;
/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/
public interface TimeService {

    Time selectAllByUsername(String userName);

    int selectByUsername(String userName);

    void insert(Time time);

    boolean update(String dateString,String userName);
}
