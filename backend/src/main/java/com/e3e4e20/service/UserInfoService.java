package com.e3e4e20.service;

import com.e3e4e20.entity.mapper.UserInfoEntity;
import com.e3e4e20.exception.FailureMessageException;

import java.util.List;

/*
Filename: UserInfoService
Created: 2023年04月08日 15时19分30秒 星期六
Author: 天龙梦雪
*/
public interface UserInfoService {
    /**
     * 添加用户信息
     * @param userInfoEntity 用户信息
     * @return true or throw exception
     */
    boolean addUserinfo (UserInfoEntity userInfoEntity) throws FailureMessageException;

    /**
     * 删除用户信息
     * @param userid 用户唯一标识
     * @return true or throw exception
     */
    boolean deleteUserInfo (String userid) throws FailureMessageException;

    /**
     * 修改用户信息
     * @param userInfoEntity 用户信息(包括用户唯一标识和修改的用户信息)
     * @return true or throw exception
     */
    boolean modifyUserInfoById (UserInfoEntity userInfoEntity) throws FailureMessageException;

    /**
     * 查询全部的用户的用户信息
     * @return 用户信息的列表,或者为 null
     */
    List<UserInfoEntity> selectAllUserInfo();

    /**
     * 校验用户是否已经存在用户信息
     * @param userid 用户唯一标识
     * @return true or false
     */
    boolean userIsNotNull (String userid);

    /**
     * 根据用户唯一标识获取用户基本信息
     * @param userid 用户唯一标识
     * @return 用户基本信息 || null
     */
    UserInfoEntity getUserInfoByUserid (String userid);
}
