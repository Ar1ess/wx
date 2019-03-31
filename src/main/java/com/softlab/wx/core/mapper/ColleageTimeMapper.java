package com.softlab.wx.core.mapper;

import com.softlab.wx.core.model.vo.ColleageContent;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/
@Mapper
@Repository
public interface ColleageTimeMapper {


    @Select("SELECT bumen,title,detail,time FROM colleagecontent WHERE bumen=#{tu}")
    ColleageContent selectContent(@Param("tu") String tu);
    @Select("SELECT bumen,closable,tip FROM colleagecontent")
    List<ColleageContent> selectTip();

}
