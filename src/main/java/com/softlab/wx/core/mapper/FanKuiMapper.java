package com.softlab.wx.core.mapper;

import com.softlab.wx.core.model.vo.FanKui;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/
@Mapper
@Repository
public interface FanKuiMapper {

    /** fid; name; number; phoneNumber; collageMajor; detail;**/

    /**
     * 反馈数据新增
     */
    @Insert("INSERT INTO fank(name,number,phone_number,collage_major,detail ) VALUES (#{name},#{number},#{phoneNumber},#{collageMajor},#{detail})")
    void addFanKui(FanKui fanKui);
}
