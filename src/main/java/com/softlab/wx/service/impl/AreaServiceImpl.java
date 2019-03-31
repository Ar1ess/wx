package com.softlab.wx.service.impl;

import com.softlab.wx.core.mapper.AreaMapper;
import com.softlab.wx.core.model.vo.Area;
import com.softlab.wx.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by LiXiwen on 2019/3/25.
 *
 **/
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    /**
     * 通过id查询
     * @param userId
     * @return
     */
    @Override
    public Area selectAreaById(Integer userId) {
        return areaMapper.selectById(userId);
    }

    /**
     * 添加报修数据
     * @param area
     * @return
     */
    @Override
    public boolean addArea(Area area){
        boolean flag=false;
        try{
            areaMapper.addArea(area);
            flag=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
