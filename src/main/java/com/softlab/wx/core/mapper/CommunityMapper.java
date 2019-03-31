package com.softlab.wx.core.mapper;

import com.softlab.wx.core.mapper.provider.CommunityProvider;
import com.softlab.wx.core.model.vo.Community;
import com.softlab.wx.core.model.vo.Comment;
import com.sun.javafx.collections.MappingChange;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * author Aries
 * date 2019-3-13
 */

@Mapper
@Repository
public interface CommunityMapper {

    /**
     * 获取文章详情
     */

    @SelectProvider(type = CommunityProvider.class, method = "selectCommunityByCondition")

    List<Community> selectCommunityByCondition(Community community);


    /**
     * 获取所有文章
     */
    @Select("SELECT community_systemId as systemId, community_title as title, community_writer as writer, community_pic as pic," +
            " community_time as time, community_content as content, community_viewsNumber as viewsNumber, community_likesNumber " +
            "as likesNumber, community_commentsNumber as commentsNumber FROM community_article ORDER BY community_time DESC")
    List<Community> selectAllCommunity();


    /**
     *
     * 文章查询
     */
    @Select("SELECT community_systemId as systemId, community_title as title, community_writer as writer, community_pic as pic," +
            " community_time as time, community_content as content, community_viewsNumber as viewsNumber, community_likesNumber " +
            "as likesNumber, community_commentsNumber as commentsNumber FROM community_article WHERE Community_title LIKE CONCAT(CONCAT('%',#{keyword},'%'))")
    List<Community> selectCommunityByKeyword(@Param("keyword") String keyword);


    /**
     * 发布帖子
     */
    //@InsertProvider(type = CommunityProvider.class, method = "insertCommunity")
    @Insert("INSERT INTO community_article(community_title, community_content, " +
            "community_writer, community_pic, community_pic1, community_pic2, community_time) VALUES (#{title}, #{content}, #{writer}, #{pic}, #{pic1}, #{pic2}, #{time})" )
    int insertCommunity(Community community);

    /**
     * 指定帖子发布评论
     */
    //@InsertProvider(type = CommunityProvider.class, method = "insertCommuntiyComment")
    @Insert("INSERT INTO community_comment(comment_isAnonym, comment_content, comment_communityId, " +
            "comment_writer, comment_pic, comment_time) VALUES (#{isAnonym}, #{content}, #{communityId}, #{writer}, #{pic}, #{time})" )
    int insertCommunityComment(Comment comment);


    /**
     * 查找指定帖子下所有评论
     */
    @Select("SELECT comment_systemId as systemId, comment_communityId as communityId, comment_writer as writer," +
            " + comment_pic as pic, comment_time as time, comment_content as content, comment_likesNumber as likesNumber," +
            " comment_isAnonym as isAnonym FROM community_comment WHERE comment_communityId=#{systemId}")
    List<Comment> selectAllComment(@Param("systemId") Integer systemId);


    /**
     * 查寻某用户的所有问题
     */
    @Select("SELECT community_systemId as systemId, community_title as title, community_writer as writer, community_pic as pic," +
            " community_time as time, community_content as content, community_viewsNumber as viewsNumber, community_likesNumber " +
            "as likesNumber, community_commentsNumber as commentsNumber FROM community_article WHERE community_writer = #{writer}")
    List<Community> selectAllCommunityByWriter(@Param("writer") String writer);


    /**
     * 删除某用户的问题
     */
    @Delete("DELETE FROM community_article WHERE community_systemId=#{systemId}")
    int deleteCommunity(@Param("systemId") Integer systemId);



}
