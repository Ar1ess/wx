package com.softlab.wx.core.model.vo;

/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/

public class Area {
    /**
     * 自定义增长id
     */
    private Integer areaId;
    /**
     * 坏的区域名字
     */
    private String areaLocation;
    /**
     * 坏的描述
     */
    private String areaContext;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户学号
     */
    private String userNum;
    /**
     * 用户电话
     */
    private String userPhone;

    public Area(){ }

    public Integer getAreaId() { return areaId; }

    public void setAreaId(Integer areaId) { areaId = areaId; }

    public String getAreaLocation() {return areaLocation; }

    public void setAreaLocation(String areaLocation) { this.areaLocation = areaLocation; }

    public String getAreaContext() { return areaContext; }

    public void setAreaContext(String areaContext) { this.areaContext = areaContext; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserNum() { return userNum; }

    public void setUserNum(String userNum) { this.userNum = userNum; }

    public String getUserPhone() { return userPhone; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }
}
