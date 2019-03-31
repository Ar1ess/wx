package com.softlab.wx.core.mapper;

import com.softlab.wx.core.model.vo.Area;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/


/**
 * 在以前的Dao层这块，hibernate和mybatis
 * 都可以使用注解或者使用mapper配置文件。在这里我们使用spring的JPA来完成基本的增删改查。
 * 说明:
 * 一般有两种方式实现与数据库实现CRUD：
 * 第一种是xml的mapper配置。
 * 第二种是使用注解，@Insert、@Select、@Update、@Delete 这些来完成。本篇使用的是第二种
 */
@Mapper
@Repository
public interface AreaMapper {
    /**
     * 根据系统ID查询地点信息
     */
    @Select("SELECT area_id AS arearId,area_location AS areaLocation,area_context AS areaContext," +
            "user_name AS userName,user_num AS userNum," +
            "user_phone AS userPhone FROM mend where area_id=#{areaId}")
    Area selectById(@Param("areaId") Integer areaId);

    /**
     * 报修数据新增
     */
    @Insert("INSERT INTO mend(area_location,area_context ,user_name,user_num ,user_phone ) VALUES (#{areaLocation},#{areaContext},#{userName},#{userNum},#{userPhone})")
    void addArea(Area area);

}
