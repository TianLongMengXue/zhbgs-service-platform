package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.UserInfoEntity;
import com.e3e4e20.exception.FailureMessageException;
import com.e3e4e20.mapper.UserInfoMapper;
import com.e3e4e20.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
Filename: UserInfoServiceImplement
Created: 2023年04月08日 15时20分44秒 星期六
Author: 天龙梦雪
*/
@Service(value = "userInfoService")
public class UserInfoServiceImplement implements UserInfoService {
    @Resource(type = UserInfoMapper.class)
    private UserInfoMapper userInfoMapper;
    private final Logger logger = LoggerFactory.getLogger("Class:UserInfoServiceImplement");

    @Override
    public boolean deleteUserInfo(String userid) throws FailureMessageException {
        int result = 0;
        try {
            result = userInfoMapper.deleteUserInfoByUserid(userid);
        } catch (Exception e) {
            logger.error("deleteUserInfo:ERROR:" + e.toString());
        }
        if (1 != result) {
            logger.debug("deleteUserInfo:删除人员:" + userid + "的基本信息失败!");
            throw new FailureMessageException("删除信息失败!");
        }
        logger.debug("deleteUserInfo:删除人员:" + userid + "基本信息成功!");
        return true;
    }

    @Override
    public boolean modifyUserInfoById(UserInfoEntity userInfoEntity) throws FailureMessageException {
        int result = 0;
        try {
            result = userInfoMapper.updateUserInfoByUserid(userInfoEntity);
        } catch (Exception e) {
            logger.error("modifyUserInfoById:ERROR:" + e.toString());
        }
        if (1 != result) {
            logger.error("modifyUserInfoById:人员:" + userInfoEntity.getUserid() + ",修改信息失败!");
            throw new FailureMessageException("修改信息失败!");
        }
        logger.debug("modifyUserInfoById:人员:" + userInfoEntity.getUserid() + ",修改信息成功!");
        return true;
    }

    @Override
    public List<UserInfoEntity> selectAllUserInfo() {
        List<UserInfoEntity> userInfoEntityList = null;
        try {
            userInfoEntityList = userInfoMapper.selectAllUserInfo();
        } catch (Exception e) {
            logger.error("selectAllUserInfo:ERROR:" + e.toString());
        }
        if (null == userInfoEntityList || 0 == userInfoEntityList.size()) {
            logger.error("selectAllUserInfo:本系统内没有存储人员信息!");
        } else {
            logger.debug("selectAllUserInfo:本系统内存储的人员信息如下:" + userInfoEntityList.toString());
        }
        return userInfoEntityList;
    }

    @Override
    public boolean userIsNotNull(String userid) {
        UserInfoEntity result = null;
        try {
            // System.out.println(userInfoMapper.selectUserInfoByUserid(userid));
            result = userInfoMapper.selectUserInfoByUserid(userid);
        } catch (Exception e) {
            logger.error("userIsNotNull:ERROR:" + e.toString());
        }
        if (null != result) {
            logger.debug("userIsNotNull:检索到用户唯一标识:" + userid + ",基本信息:" + result.toString());
            return true;
        } else {
            logger.error("userIsNotNull:用户唯一标识:" + userid + ",不存在该用户的基本信息!");
            return false;
        }
    }

    @Override
    public UserInfoEntity getUserInfoByUserid(String userid) {
        UserInfoEntity userInfoEntity = null;
        try {
            userInfoEntity = userInfoMapper.selectUserInfoByUserid(userid);
        } catch (Exception e) {
            logger.error("getUserInfoByUserid:ERROR:" + e.toString());
        }
        if (null != userInfoEntity) {
            logger.debug("getUserInfoByUserid:用户唯一标识:" + userid + ",基本信息:" + userInfoEntity.toString());
        } else {
            logger.error("getUserInfoByUserid:用户唯一标识:" + userid + ",不存在该用户的基本信息!");
        }
        return userInfoEntity;
    }

    @Override
    public boolean addUserinfo(UserInfoEntity userInfoEntity) throws FailureMessageException {
        int result = 0;
        try {
            result = userInfoMapper.insertUserInfo(userInfoEntity);
        } catch (Exception e) {
            logger.error("addUserinfo:ERROR:" + e.toString());
        }
        if (1 != result) {
            logger.error("addUserInfo:添加人员信息失败!" + userInfoEntity);
            throw new FailureMessageException("添加人员信息失败!");
        }
        logger.debug("addUserInfo:添加人员信息成功!" + userInfoEntity);
        return true;
    }
}
