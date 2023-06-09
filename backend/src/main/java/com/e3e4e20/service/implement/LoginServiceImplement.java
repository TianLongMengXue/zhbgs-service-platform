package com.e3e4e20.service.implement;

import com.e3e4e20.config.CommonsCodecConfig;
import com.e3e4e20.constant.ProjectDefaultConfig;
import com.e3e4e20.entity.mapper.LoginEntity;
import com.e3e4e20.exception.ErrorMessageException;
import com.e3e4e20.exception.FailureMessageException;
import com.e3e4e20.mapper.LoginMapper;
import com.e3e4e20.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
Filename: LoginServiceImplement
Created: 2023年04月08日 14时00分51秒 星期六
Author: 天龙梦雪
*/
@Service(value = "loginService")
public class LoginServiceImplement implements LoginService {
    @Resource(type = LoginMapper.class)
    private LoginMapper loginMapper;
    private final Logger logger = LoggerFactory.getLogger("Class:LoginServiceImplement");
    /*private int passwordMinLength = 6;
    private int passwordMaxLength = 20;
    private String defaultPasswordEncoder = "";
    private String defaultAvatar = "";*/

    /*public LoginServiceImplement(int passwordMinLength, int passwordMaxLength, String defaultPasswordEncoder, String defaultAvatar) throws FailureMessageException {
        if (passwordMinLength <= 0) {
            logger.error("init error: 用户密码最短长度不能小于等于 0 !");
            throw new FailureMessageException("用户名称不存在或者用户密码错误!");
        }
        if (passwordMaxLength <= 0) {
            logger.error("init error: 用户密码最大长度不能小于等于 0 !");
            throw new FailureMessageException("用户名称不存在或者用户密码错误!");
        }
        if (passwordMaxLength <= passwordMinLength){
            logger.error("init error: 用户密码最短长度不能小于等于用户密码最大长度!");
            throw new FailureMessageException("用户名称不存在或者用户密码错误!");
        }
        this.passwordMinLength = passwordMinLength;
        this.passwordMaxLength = passwordMaxLength;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        if (null == defaultAvatar || defaultAvatar.length() <= 0) {
            logger.error("init error: 用户默认头像为空!");
            throw new FailureMessageException("用户默认头像为空!");
        }
        this.defaultAvatar = defaultAvatar;
    }*/

    /*@Override
    public boolean useridIsNotNull(String userid) throws FailureMessageException {
        if (null == userid || userid.length() <= 0) {
            logger.error("useridIsNotNull: userid is null,用户唯一标识为空!");
            throw new FailureMessageException("用户名称不存在或者用户密码错误!");
        }
        return true;
    }*/

    /*@Override
    public boolean usernameIsNotNull(String username) throws FailureMessageException {
        if (null == username || username.length() <= 0) {
            logger.error("usernameIsNotNull: username is null,用户名称为空!");
            throw new FailureMessageException("用户名称不存在或者用户密码错误!");
        }
        return true;
    }*/

    /*@Override
    public boolean passwordIsNotNull(String password) throws FailureMessageException {
        if (null == password || password.length() <= 0) {
            logger.error("passwordIsNotNull: password is null,用户密码为空!");
            throw new FailureMessageException("用户名称不存在或者用户密码错误!");
        }
        return true;
    }*/


    /*@Override
    public boolean passwordLengthIsConformity(String password) throws FailureMessageException {
        if (this.passwordMinLength > password.length()) {
            logger.error("passwordLengthIsConformity: password is too short,用户密码长度过短!");
            throw new FailureMessageException("用户名称不存在或者用户密码错误!");
        } else if (this.passwordMaxLength < password.length()) {
            logger.error("passwordLengthIsConformity: password is too long,用户密码长度过长!");
            throw new FailureMessageException("用户名称不存在或者用户密码错误!");
        }
        return true;
    }*/

    @Override
    public boolean addLoginUser(LoginEntity loginEntity) throws ErrorMessageException {
        // 判断参数 userid 和 username 是否为空
        /*this.useridIsNotNull(userid);
        this.usernameIsNotNull(username);*/
        // 封装 login 实体类,以便添加到数据库中
        /*loginEntity.setUserid(userid);
        loginEntity.setUsername(username);
        loginEntity.setPassword(this.defaultPasswordEncoder);
        loginEntity.setAvatar(this.defaultAvatar);
        loginEntity.setPassword(passwordEncoder);
        loginEntity.setAvatar(avatar);*/
        logger.debug("addLoginUser: 正在授予" + "userid=" + loginEntity.getUserid() + "登录权限并添加登录信息: " + loginEntity);
        // 将封装好的 login 实体类添加到数据库中
        int result = 0;
        try {
            result = loginMapper.addLoginUser(loginEntity);
        } catch (Exception exception) {
            logger.error("addLoginUser: ERROR: " + exception);
            // logger.error("用户唯一标识:" + userid + ",用户名称:" + username + ";授予登录权限失败!");
        }
        // 添加到数据库中的结果为 1 表示该条记录添加成功,否则失败
        if (1 == result) {
            logger.debug("addLoginUser: 用户唯一标识=" + loginEntity.getUserid() + "用户名称=" + loginEntity.getUsername() + "授予登录权限成功!");
            return true;
        } else {
            logger.error("addLoginUser: 用户唯一标识:" + loginEntity.getUserid() + ",用户名称:" + loginEntity.getUsername() + ";授予登录权限失败!");
            throw new ErrorMessageException("授予登录权限失败!");
        }
    }

    @Override
    public boolean deleteLoginUser(String userid) throws ErrorMessageException {
        // 判断参数是否为空
        /*this.useridIsNotNull(userid);*/
        // 从数据库中删除用户唯一标识=userid的登录信息
        Integer result = 0;
        try {
            result = loginMapper.deleteLoginUserByUserid(userid);
        } catch (Exception exception) {
            logger.error("deleteLoginUser: ERROR: " + exception);
            // return false;
        }
        // 删除成功的结果为1,否则删除失败
        if (1 == result) {
            logger.debug("deleteLoginUser: 人员唯一标识=" + userid + ",撤销登录权限成功!");
            return true;
        } else {
            logger.error("deleteLoginUser: 人员唯一标识=" + userid + ",撤销登录权限失败!");
            throw new ErrorMessageException("撤销登录权限失败!");
        }
    }

    @Override
    public boolean modifyLoginPassword(String userid, String passwordEncoder) throws ErrorMessageException {
        // 判断参数是否为空
        /*this.useridIsNotNull(userid);*/
        // 修改数据库中登录信息中的用户密码字段
        Integer result = 0;
        try {
            /*result = loginMapper.updatePasswordByUserid(userid, this.defaultPasswordEncoder);*/
            result = loginMapper.updatePasswordByUserid(userid, passwordEncoder);
        } catch (Exception exception) {
            logger.error("modifyLoginPassword: ERROR: " + exception);
            // return false;
        }
        // 修改成功的结果为1,否则修改失败
        if (1 == result) {
            logger.debug("modifyLoginPassword: 用户唯一标识=" + userid + ",修改登录密码成功!");
            return true;
        } else {
            logger.error("modifyLoginPassword: 用户唯一标识=" + userid + ",修改登录密码失败!");
            throw new ErrorMessageException("修改登录密码失败!");
        }
    }

    @Override
    public String haveLoginPrivilege(String username, String passwordEncoder) throws ErrorMessageException {
//        String passwordEncode = new CommonsCodecConfig().sha512HexEncode(password);
        String userid = null;
        try {
            userid = loginMapper.selectUserid(username, passwordEncoder);
        } catch (Exception exception) {
            logger.error("haveLoginPrivilege: ERROR: " + exception);
        }
        if (null != userid) {
            logger.debug("haveLoginPrivilege: "+username + "的用户唯一标识为" + userid);
            return userid;
        } else {
            logger.error("haveLoginPrivilege: "+username + "不存在于数据库中!");
            throw new ErrorMessageException("该人员不具有登录权限!");
        }
    }

    @Override
    public boolean haveLoginPrivilege(String userid) {
        LoginEntity loginEntity = null;
        try {
            loginEntity = loginMapper.selectUserByUserid(userid);
        } catch (Exception exception) {
            logger.error("haveLoginPrivilege: ERROR: " + exception);
            // return false;
        }
        if (null != loginEntity) {
            logger.debug("haveLoginPrivilege: 用户唯一标识:" + userid + ",具有登录权限!");
            return true;
        } else {
            logger.error("haveLoginPrivilege: 用户唯一标识:" + userid + ",不具有登录权限!");
            return false;
        }
    }

    /*@Override
    public boolean modifyLoginPassword(String userid) {
        Integer result = 0;
        String passwordEncode = new CommonsCodecConfig().sha512HexEncode(ProjectDefaultConfig.PROJECT_DEFAULT_PASSWORD);
        try {
            result = loginMapper.updatePasswordByUserid(userid, passwordEncode);
        } catch (Exception e) {
            logger.error("modifyLoginPassword:ERROR:" + e.getMessage());
            // return false;
        }
        if (1 == result) {
            logger.debug("人员:" + userid + "重置登录密码成功!");
            return true;
        } else {
            logger.error("人员:" + userid + "重置登录密码失败!");
            return false;
        }
    }*/

    @Override
    public String getUserAvatar (String userid) throws ErrorMessageException {
        String result = null;
        try {
            result = loginMapper.selectUserAvatar(userid);
        } catch (Exception exception) {
            logger.error("getUserAvatar: ERROR: "+exception);
        }
        if (null != result) {
            logger.debug("getUserAvatar: userid="+userid+",avatar="+result+"获取用户头像文件名称成功!");
            return result;
        } else {
            logger.error("getUserAvatar: userid="+userid+",获取用户头像文件名称失败!");
            throw new ErrorMessageException("该用户不存在用户头像或者用户头像文件格式不正确无法显示!");
        }
    }

    @Override
    public boolean modifyUserAvatar (String userid, String avatar) throws ErrorMessageException {
        int result = 0;
        try {
            result = loginMapper.updateUserAvatar(userid, avatar);
        } catch (Exception exception) {
            logger.error("modifyUserAvatar: ERROR: "+exception);
        }
        if (1 == result) {
            logger.debug("modifyUserAvatar: userid="+userid+"avatar="+avatar+",用户头像文件名称已修改!");
            return true;
        } else {
            logger.error("modifyUserAvatar: userid="+userid+"avatar="+avatar+",修改用户头像文件名称失败!");
            throw new ErrorMessageException("修改用户头像失败!");
        }
    }
}