package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.UserInfoEntity;
import com.e3e4e20.exception.ErrorMessageException;
import com.e3e4e20.mapper.UserInfoMapper;
import com.e3e4e20.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public boolean deleteUserInfo(String userid) {
        int result = 0;
        try {
            result = userInfoMapper.deleteUserInfoByUserid(userid);
        } catch (Exception exception) {
            logger.error("deleteUserInfo: ERROR: " + exception.getMessage());
        }
        if (1 != result) {
            logger.debug("deleteUserInfo: 删除人员: " + userid + "的基本信息失败!");
            throw new ErrorMessageException("删除人员信息失败!");
        }
        logger.debug("deleteUserInfo: 删除人员: " + userid + "的基本信息成功!");
        return true;
    }

    @Override
    @Transactional
    public boolean modifyUserInfoById(UserInfoEntity userInfoEntity) {
        int result = 0;
        try {
            result = userInfoMapper.updateUserInfoByUserid(userInfoEntity);
        } catch (Exception exception) {
            logger.error("modifyUserInfoById: ERROR: " + exception.getMessage());
        }
        if (1 != result) {
            logger.error("modifyUserInfoById: 人员: " + userInfoEntity.getId() + ",修改信息失败!");
            throw new ErrorMessageException("修改人员信息失败!");
        }
        logger.debug("modifyUserInfoById: 人员: " + userInfoEntity.getId() + ",修改信息成功!");
        return true;
    }

    @Override
    @Transactional
    public List<UserInfoEntity> selectAllUserInfo() {
        List<UserInfoEntity> userInfoEntityList = null;
        try {
            userInfoEntityList = userInfoMapper.selectAllUserInfo();
        } catch (Exception exception) {
            logger.error("selectAllUserInfo: ERROR: " + exception.getMessage());
        }
        if (null == userInfoEntityList || 0 == userInfoEntityList.size()) {
            logger.error("selectAllUserInfo: 本系统内没有存储人员信息!");
            throw new ErrorMessageException("暂无人员信息!");
        } else {
            logger.debug("selectAllUserInfo: 本系统内存储的人员信息如下: " + userInfoEntityList);
        }
        return userInfoEntityList;
    }

    @Override
    @Transactional
    public boolean userIsNotNull(String userid) {
        UserInfoEntity result = null;
        try {
            result = userInfoMapper.selectUserInfoByUserid(userid);
        } catch (Exception exception) {
            logger.error("userIsNotNull: ERROR: " + exception.getMessage());
        }
        if (null != result) {
            logger.debug("userIsNotNull: 检索到用户唯一标识: " + userid + ",基本信息: " + result);
            return true;
        } else {
            logger.error("userIsNotNull: 用户唯一标识: " + userid + ",不存在该用户的基本信息!");
            return false;
        }
    }

    @Override
    @Transactional
    public UserInfoEntity getUserInfoByUserid(String userid) {
        UserInfoEntity userInfoEntity = null;
        try {
            userInfoEntity = userInfoMapper.selectUserInfoByUserid(userid);
        } catch (Exception exception) {
            logger.error("getUserInfoByUserid: ERROR: " + exception.getMessage());
        }
        if (null != userInfoEntity) {
            logger.debug("getUserInfoByUserid: 用户唯一标识: " + userid + ",基本信息: " + userInfoEntity);
        } else {
            logger.error("getUserInfoByUserid: 用户唯一标识: " + userid + ",不存在该用户的基本信息!");
            throw new ErrorMessageException("该人员不存在!");
        }
        return userInfoEntity;
    }

    @Override
    @Transactional
    public boolean addUserinfo(UserInfoEntity userInfoEntity) {
        int result = 0;
        try {
            result = userInfoMapper.insertUserInfo(userInfoEntity);
        } catch (Exception exception) {
            logger.error("addUserinfo: ERROR: " + exception.getMessage());
        }
        if (1 != result) {
            logger.error("addUserInfo: 添加人员信息失败! " + userInfoEntity);
            throw new ErrorMessageException("添加人员信息失败!");
        }
        logger.debug("addUserInfo: 添加人员信息成功! " + userInfoEntity);
        return true;
    }
}
