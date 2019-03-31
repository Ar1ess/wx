package com.softlab.wx.core.model.vo;
/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/
public class Time {
    private int tid;
    private String userName;
    private String time;


    public Time(){

    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
