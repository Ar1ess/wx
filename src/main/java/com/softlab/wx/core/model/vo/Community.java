package com.softlab.wx.core.model.vo;
/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/
import java.util.Date;


/**
 * author Aries
 * date 2019-3-13
 */

public class Community {

    /**
     * 文章ID
     */
    private Integer systemId;

    /**
     * 文章题目
     */
    private String title;

    /**
     * 文章作者
     */
    private String writer;

    /**
     * 文章内容图片
     */
    private String pic;

    /**
     * 文章内容图片1
     */
    private String pic1;

    /**
     * 文章内容图片2
     */
    private String pic2;

    /**
     * 文章完成时间
     */
    private Date time;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 文章浏览量
     */
    private Integer viewsNumber;

    /**
     * 文章点赞数
     */
    private Integer likesNumber;

    /**
     * 文章评论数
     */
    private Integer commentsNumber;


    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic1() { return pic1; }

    public void setPic1(String pic1) { this.pic1 = pic1; }

    public String getPic2() { return pic2; }

    public void setPic2(String pic2) { this.pic2 = pic2; }

    public Date getTime() { return time; }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(Integer viewsNumber) { this.viewsNumber = viewsNumber; }

    public Integer getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(Integer likesNumber) {
        this.likesNumber = likesNumber;
    }

    public Integer getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(Integer commentsNumber) {
        this.commentsNumber = commentsNumber;
    }





}
