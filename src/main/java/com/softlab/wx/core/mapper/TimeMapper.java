package com.softlab.wx.core.mapper;

import com.softlab.wx.core.model.vo.Time;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/
@Mapper
@Repository
public interface TimeMapper {

    @Select("SELECT COUNT(*) FROM time WHERE username=#{userName}")
    int selectByUsername(@Param("userName") String userName);

    @Select("SELECT * FROM time WHERE username=#{userName}")
    Time selectAllByUsername(@Param("userName") String userName);

    @Insert("INSERT INTO time (username,time)VALUES(#{userName},#{time})")
    void insert(Time time);

    @Update("update time set time=#{dateString} where username=#{userName}")
    boolean update(@Param("dateString") String dateString , @Param("userName") String userName);
}
