package com.softlab.wx.service.impl;

import com.qiniu.api.auth.AuthException;
import com.softlab.wx.common.WxException;
import com.softlab.wx.common.util.CommonUtil;
import com.softlab.wx.common.util.ExecuteResult;
import com.softlab.wx.common.util.QiniuUtil;
import com.softlab.wx.core.mapper.CommunityMapper;
import com.softlab.wx.core.model.vo.Comment;
import com.softlab.wx.core.model.vo.Community;
import com.softlab.wx.service.CommunityService;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityMapper communityMapper;

    public CommunityServiceImpl (CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }

    @Override
    public Map<String, Object> getCommunityDetailByCommunitySystemId(Community community) throws WxException {
        Map<String, Object> map = new HashMap<>(8);
        if (null != community && null != community.getSystemId()){
            List<Community> communityList = communityMapper.selectCommunityByCondition(community);
            if (null != communityList && 1 == communityList.size()){
                community = communityList.get(0);
                map.put("systemId", community.getSystemId());
                map.put("title", community.getTitle());
                map.put("writer", community.getWriter());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("time", formatter.format(community.getTime()));
                map.put("content", community.getContent());
                if (null != community.getPic()){
                    map.put("pic", community.getPic());
                }
                if (null != community.getPic1()){
                    map.put("pic1", community.getPic());
                }
                if (null != community.getPic2()){
                    map.put("pic2", community.getPic());
                }
                map.put("viewsNumber", community.getViewsNumber());
                map.put("likesNumber", community.getLikesNumber());
                map.put("commentsNumber", community.getCommentsNumber());
            }
            return map;
        }
        else{
            throw new WxException("系统异常!");

        }

    }

    @Override
    public List<Map<String, Object>> getAllCommunity() throws WxException{
        List<Map<String, Object>> al = new ArrayList<>();
        Community community = new Community();
        List<Community> communityList = communityMapper.selectAllCommunity();
        if (null != communityList){
            for (Community community1 : communityList){
                Map<String, Object> map = new HashMap<>(8);
                map.put("id",community1.getSystemId());
                map.put("writer", community1.getWriter());
                map.put("title", community1.getTitle());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("time", formatter.format(community1.getTime()));
                map.put("viewsNumber", community1.getViewsNumber());
                al.add(map);
            }
            return al;
        }
        else{
            throw new WxException("系统异常!");
        }

    }

    @Override
    public List<Map<String, Object>> getCommunityByKeyword(String keyword) throws WxException{
        List<Map<String, Object>> al = new ArrayList<>();
        Community community = new Community();
        List<Community> communityList = communityMapper.selectCommunityByKeyword(keyword);
        if (null != communityList){
            for (Community community1 : communityList){
                Map<String, Object> map = new HashMap<>(8);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("time", formatter.format(community1.getTime()));
                map.put("viewsNumber", community1.getViewsNumber());
                map.put("writer", community1.getWriter());
                map.put("title", community1.getTitle());
                al.add(map);
            }
            return al;
        }
        else{
            throw new WxException("系统异常!");
        }
    }



    @Override
    public boolean insertCommunity(Community community, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws WxException{
        boolean flag = false;
        if (null != community){
            if (null != file1) {
                String str = qiniuUpload(file1);
                community.setPic(str);
            }
            if (null != file2) {
                String str1 = qiniuUpload(file1);
                community.setPic1(str1);
            }
            if (null != file3) {
                String str2 = qiniuUpload(file1);
                community.setPic2(str2);
            }
            community.setTitle(community.getTitle());
            community.setContent(community.getContent());
            community.setLikesNumber(0);
            community.setCommentsNumber(0);
            community.setViewsNumber(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time = null;
            try {
                time= sdf.parse(sdf.format(new Date()));
            } catch (ParseException e) {

                e.printStackTrace();
            }
            community.setTime(time);
            int success = communityMapper.insertCommunity(community);
            if (0 < success){
                flag =true;
            }
        } else {
            throw new WxException("系统异常!");
        }
        return flag;
    }



    @Override
    public boolean insertCommunityComment(Comment comment, Integer id) throws WxException{
        boolean flag = false;
        if (null != comment){
            comment.setLikesNumber(0);
            comment.setCommunityId(id);
            Community community = new Community();
            community.setSystemId(id);
            List<Community> communityList = communityMapper.selectCommunityByCondition(community);
            if (null != communityList && 1 == communityList.size()){
                community = communityList.get(0);
                community.setCommentsNumber(community.getCommentsNumber() + 1);
            }
            int success = communityMapper.insertCommunityComment(comment);
            if (0 < success){
                flag = true;
            }
        } else {
            throw new WxException("系统异常!");
        }
        return flag;
    }



    @Override
    public List<Map<String, Object>> getAllComment(Community community) throws WxException{
        List<Map<String, Object>> Al = new ArrayList<>();
        int systemId = community.getSystemId();
        List<Comment> commentList = communityMapper.selectAllComment(systemId);
        if (null != commentList){
            for (Comment comment1 : commentList){
                Map<String, Object> map = new HashMap<>(8);
                map.put("writer", comment1.getWriter());
                map.put("content", comment1.getContent());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("time", formatter.format(comment1.getTime()));
                map.put("likesNumber", comment1.getLikesNumber());
                Al.add(map);
            }
            return Al;
        }
        else{
            throw new WxException("系统异常!");
        }
    }


    @Override
    public List<Map<String, Object>> selectAllCommunityByWriter(String writer) throws WxException{
        List<Map<String, Object>> Al = new ArrayList<>();
        List <Community> communityList = communityMapper.selectAllCommunityByWriter(writer);
        if (null != communityList){
            for (Community community1 : communityList){
                Map<String, Object> map = new HashMap<>(8);
                map.put("title", community1.getTitle());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("time", formatter.format(community1.getTime()));
                map.put("commentsNumber", community1.getCommentsNumber());
                Al.add(map);
            }
        } else{
            throw new WxException("系统异常!");
        }
        return Al;
    }


    @Override
    public int countAllCommunityByWriter(String writer) throws WxException{
        List <Community> communityList = communityMapper.selectAllCommunityByWriter(writer);
        if (null != communityList){
            return communityList.size();
        } else{
            throw new WxException("系统异常!");
        }
    }


    @Override
    public boolean deleteCommunityBySystemId(Community community) throws WxException{
        boolean flag = false;
        if (null != community) {
            int systemId = community.getSystemId();
            int success = communityMapper.deleteCommunity(systemId);
            if (0 < success){
                flag = true;
            } else {
                throw new WxException("系统异常!");
            }
        }
        return flag;
    }


    @Override
    public String qiniuUpload(MultipartFile file) {
        //System.out.println(file.getName());
        ExecuteResult<String> executeResult = new ExecuteResult<String>();
        QiniuUtil qiniuUtil = new QiniuUtil();
        CommonUtil commonUtil = new CommonUtil();
        try {
            String filenameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());

            File file_up = commonUtil.multipartToFile(file);

            executeResult = qiniuUtil.uploadFile(file_up, filenameExtension);

            if (!executeResult.isSuccess()) {
                return "失败" + executeResult.getErrorMessages();
            }

        } catch (AuthException | JSONException e) {
            //logger.error("AuthException", e);
            System.out.println("AuthException");
        }
        //System.out.println(executeResult.getResult());
        return executeResult.getResult();
    }







}
