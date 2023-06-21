package com.e3e4e20.service;

import com.e3e4e20.entity.mapper.LoginEntity;
import com.e3e4e20.exception.ErrorMessageException;
import com.e3e4e20.exception.FailureMessageException;

/*
Filename: LoginService
Created: 2023年04月08日 14时00分12秒 星期六
Author: 天龙梦雪
*/

/**
 * 1、登录校验
 * 2、授予登录权限
 * 3、撤销登录权限
 * 4、修改登录密码
 * 5、重置登录密码
 * 6、修改用户头像
 * 7、获取登录头像
 * 8、用户唯一标识非空校验
 * 9、用户名称非空校验
 * 10、用户密码非空校验
 * 11、用户密码长度校验,默认长度在[6,20]之间,包含6和20
 */
public interface LoginService {
    /**
     * 用户唯一标识是否为空
     * @param userid 用户唯一标识
     * @exception FailureMessageException
     */
//    boolean useridIsNotNull(String userid) throws FailureMessageException;

    /**
     * 校验用户名称是否为空
     *
     * @param username 用户名称
     * @return true or Exception
     */
//    boolean usernameIsNotNull(String username) throws FailureMessageException;

    /**
     * 校验用户密码是否为空
     *
     * @param password 用户密码
     * @return true or Exception
     */
//    boolean passwordIsNotNull(String password) throws FailureMessageException;

    /**
     * 校验用户密码长度是否符合
     *
     * @param password 用户密码
     * @return true or Exception
     */
//    boolean passwordLengthIsConformity(String password) throws FailureMessageException;

    /**
     * 给指定人员授予登录权限
     * @param loginEntity 人员的登录信息
     * @return true or throw Exception
     * @throws ErrorMessageException 授予人员登录权限功能执行异常中断
     */
    boolean addLoginUser(LoginEntity loginEntity) throws ErrorMessageException;

    /**
     * 撤销指定人员的登录权限
     * @param userid 人员的唯一标识
     * @return true or throw Exception
     * @throws ErrorMessageException 撤销人员登录权限功能执行异常中断
     */
    boolean deleteLoginUser(String userid) throws ErrorMessageException;

    /**
     * 修改/重置指定人员登录的用户密码
     * @param userid 人员的唯一标识
     * @param passwordEncoder 人员的登录密码(已加密)
     * @return true or throw Exception
     * @throws ErrorMessageException 修改/重置人员的登录密码功能执行异常中断
     */
    boolean modifyLoginPassword(String userid, String passwordEncoder) throws ErrorMessageException;

    /**
     * 重置用户的登录密码
     * @param userid 用户唯一标识
     * @return true or false
     */
//    boolean modifyLoginPassword(String userid);

    /**
     * 通过人员的用户名称和用户密码检索数据库中是否存在该人员的登录信息,
     * 若是存在说明该人员具有登录权限,并返回该用户的用户id
     * @param username 人员登录的用户名称
     * @param passwordEncoder 人员登录的用户密码(已加密)
     * @return 人员的唯一标识
     * @throws ErrorMessageException 检索相关数据过程中异常中断
     */
    String haveLoginPrivilege(String username, String passwordEncoder) throws ErrorMessageException;

    /**
     * 根据人员的唯一标识检索数据库中是否存在该人员的登录信息,
     * 若是存在说明该人员具有登录权限
     * @param userid 人员的唯一标识
     * @return true or false
     */
    boolean haveLoginPrivilege(String userid);

    /**
     * 获取用户头像
     * @param userid 用户唯一标识
     * @return 用户头像文件名称 or throw Exception
     * @throws ErrorMessageException 检索相数据过程中异常中断
     */
    String getUserAvatar (String userid) throws ErrorMessageException;

    /**
     * 修改用户头像
     * @param userid 用户唯一标识
     * @param avatar 用户头像文件名称
     * @return true or Exception
     * @throws ErrorMessageException 修改人员登录后的用户头像功能执行异常中断
     */
    boolean modifyUserAvatar (String userid, String avatar) throws ErrorMessageException;
}
