package com.e3e4e20.controller;

import com.e3e4e20.config.CommonsCodecConfig;
import com.e3e4e20.config.Pinyin4JConfig;
import com.e3e4e20.config.TokenConfig;
import com.e3e4e20.constant.ProjectDefaultConfig;
import com.e3e4e20.entity.mapper.LoginEntity;
import com.e3e4e20.entity.mapper.UserInfoEntity;
import com.e3e4e20.exception.ErrorMessageException;
import com.e3e4e20.exception.FailureMessageException;
import com.e3e4e20.service.LoginService;
import com.e3e4e20.service.LoginTimeService;
import com.e3e4e20.service.PostService;
import com.e3e4e20.service.UserInfoService;
import com.e3e4e20.service.implement.LoginServiceImplement;
import com.e3e4e20.service.implement.LoginTimeServiceImplement;
import com.e3e4e20.service.implement.PostServiceImplement;
import com.e3e4e20.service.implement.UserInfoServiceImplement;
import com.e3e4e20.utils.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/*
Filename: LoginController
Created: 2023年04月07日 22时12分08秒 星期五
Author: 天龙梦雪
*/
@CrossOrigin
@RestController
@RequestMapping("/login")
@Api(tags = {"人员登录管理"}, value = "人员登录控制器")
@Validated
public class LoginController {
    @Resource(name = "loginService")
    private final LoginService loginService = new LoginServiceImplement();
    @Resource(name = "userInfoService")
    private final UserInfoService userInfoService = new UserInfoServiceImplement();
    @Resource(name = "postService")
    private final PostService postService = new PostServiceImplement();
    @Resource(name = "loginTimeService")
    private final LoginTimeService loginTimeService = new LoginTimeServiceImplement();
    private final Logger logger = LoggerFactory.getLogger("Class:LoginController");

    /**
     * 校验用户登录信息
     * 1、校验用户名称是否为空
     * 2、校验用户密码是否为空
     * 3、校验用户密码的长度是否合格
     * 4、校验该用户是否具有登录权限,若是具有登录权限,则生成 token,否则报错
     * 5、登录成功之后,记录登录时间
     *
     * @param username 用户名称
     * @param password 用户密码
     * @param isStore  是否保存登录信息
     * @return token userid 或者登录信息有误
     */
    @PostMapping("/check")
    @ApiOperation("根据人员的用户名称和用户密码执行登录校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称"),
            @ApiImplicitParam(name = "password", value = "用户密码"),
            @ApiImplicitParam(name = "isStore", value = "是否保存用户登录信息,若是保存token有效期为30天,否则token仅当前登录有效"),
    })
    public ResponseData getToken(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "isStore", defaultValue = "false") boolean isStore
    ) throws ParseException, FailureMessageException, ErrorMessageException {
        logger.debug("getToken: 用户名称" + username + "用户密码" + password + "是否保存用户登录信息" + isStore + ",校验登录信息!");
        // 检验数据是否为空
        if (null == username || username.length() <= 0) {
            logger.error("getToken: username is null, 用户名称名称为空!");
            throw new FailureMessageException("用户名称错误或者用户密码不正确!");
        }
        if (null == password || password.length() <= 0) {
            logger.error("getToken: password is null,username=" + username + ",用户密码为空!");
            throw new FailureMessageException("用户名称错误或者用户密码不正确!");
        }
        // 检验密码长度是否正确
        if (password.length() < ProjectDefaultConfig.MIN_PASSWORD_LENGTH) {
            logger.error("getToken: password is too short,username=" + username + "的用户密码长度: " + password.length() + ",小于最短长度" + ProjectDefaultConfig.MIN_PASSWORD_LENGTH + "!");
            throw new FailureMessageException("用户名称错误或者用户密码不正确!");
        }
        if (password.length() > ProjectDefaultConfig.MAX_PASSWORD_LENGTH) {
            logger.error("getToken: password is too long,username=" + username + "的用户密码长度: " + password.length() + "大于最大长度" + ProjectDefaultConfig.MAX_PASSWORD_LENGTH + "!");
            throw new FailureMessageException("用户名称错误或者用户密码不正确!");
        }
        // 对用户密码执行加密处理
        String passwordEncoder = new CommonsCodecConfig().sha512HexEncode(password);
        // 校验用户名称和用户密码
        String userid = loginService.haveLoginPrivilege(username, passwordEncoder);
        // 生成 token
        String token = new TokenConfig().createdToken(userid, isStore);
        // 检验 token 是否为空
        if (null == token || token.length() <= 0) {
            logger.error("getToken: userid=" + userid + ",生成 token 失败!");
            throw new ErrorMessageException("用户名称错误或者用户密码不正确!");
        }
        logger.debug("用户唯一标识:" + userid + ",用户名称:" + username + ",token为" + token);
        Map<String, String> result = new HashMap<String, String>();
        result.put("token", token);
        result.put("id", userid);
        loginTimeService.recordLoginTime(userid);
        return ResponseData.SUCCESS("用户登录成功", result);
    }

    /**
     * 为用户赋予登录权限,需要以下几点：
     * 1、被赋予登录权限的用户,基本信息必须已经存在于数据库
     * 2、被赋予登录权限的用户,不能是离/退休职工人员
     * 2、被赋予登录权限的用户,当前不能已经具有登录的权限
     *
     * @param userid 用户唯一标识
     * @return 是否成功
     */
    @PostMapping("/grant")
    @ApiOperation("根据人员唯一标识授予登录权限")
    @ApiImplicitParam(name = "id", value = "人员唯一标识")
    public ResponseData grantLoginPrivilege(@RequestParam("id") String userid) throws ParseException, ErrorMessageException {
        logger.debug("grantLoginPrivilege: 正在为人员: "+userid+",授予登录权限");
        if (!userInfoService.userIsNotNull(userid)) { // 系统里没有该人员的基本信息,不能授予登录权限
            logger.error("grantLoginPrivilege: 人员" + userid + ",没有该人员的基本信息,不能授予登录权限");
            throw new ErrorMessageException("授予登录权限失败!");
        }
        if (loginService.haveLoginPrivilege(userid)) { // 该人员已经具有登录权限,不能重复授予
            logger.error("grantLoginPrivilege: 人员唯一标识=" + userid + ",已具有登录权限,不能重复授予!");
            throw new ErrorMessageException("授予登录权限失败!");
        }
        UserInfoEntity userInfoEntity = userInfoService.getUserInfoByUserid(userid);
        if (postService.getPostNameById(userInfoEntity.getPostId()).equals("离/退休人员")) { // 离/退休人员不能赋予登录权限
            logger.error("grantLoginPrivilege: 人员唯一标识=" + userid + ",是离/退休人员,不能被授予登录权限!");
            throw new ErrorMessageException("授予登录权限失败!");
        }
        // 封装人员登录基本信息,并为该人员授予登录权限
        String name = userInfoService.getUserInfoByUserid(userid).getName();
        // 生成用户名称
        String username = new Pinyin4JConfig().getLowerPingYin(name);
        // 检验用户密码是否为空
        if (null == username || username.length() <= 0) {
            logger.error("grantLoginPrivilege: username is null, 用户名称名称为空!");
            throw new ErrorMessageException("授予登录权限失败!");
        }
        logger.debug("grantLoginPrivilege: 姓名:" + name + ",用户名称:" + username);
        // 封装人员的登录信息
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUserid(userid);
        loginEntity.setPassword(new CommonsCodecConfig().sha512HexEncode(ProjectDefaultConfig.PROJECT_DEFAULT_PASSWORD));
        loginEntity.setAvatar(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_NAME);
        // 为人员授予登录权限
        loginService.addLoginUser(loginEntity);
        // 记录人员授予登录权限的时间
        loginTimeService.recordLoginTime(userid);
        return ResponseData.SUCCESS("授予登录权限成功!", null);
    }

    /**
     * 取消授予给指定人员的登录权限,
     * 1、校验该人员是否具有登录的权限,若是存在,则取消,若是不存在,终止取消
     * 2、若是人员具有登录权限,那么需要在撤销登录权限之前需要删除所有的登录时间记录
     * 3、并且还需要删除关联的菜单和按钮的api接口权限
     *
     * @param userid 人员唯一标识
     * @return 是否成功
     */
    @PostMapping("annul")
    @ApiOperation("根据人员唯一标识撤销该人员的登录权限")
    @ApiImplicitParam(name = "id", value = "人员唯一标识")
    public ResponseData annulLoginPrivilege(@RequestParam("id") String userid) throws ErrorMessageException {
        logger.debug("annulLoginPrivilege: 正在为人员: "+userid+",撤销登录权限!");
        if (!loginService.haveLoginPrivilege(userid)) {
            logger.error("annulLoginPrivilege: 人员:" + userid + "不具有登录权限,不能撤销其登录权限!");
            throw new ErrorMessageException("撤销登录权限失败!");
        } else {
            if (!loginTimeService.terminateLoginTime(userid)) {
                logger.debug("正在删除人员信息,但是该人员:" + userid + "具有登录权限,且删除登录时间记录失败!");
                return ResponseData.FAILURE("删除人员信息失败!");
            }
            if (loginService.deleteLoginUser(userid)) {
                logger.debug("撤销人员" + userid + "的登录权限成功!");
                return ResponseData.SUCCESS("撤销登录权限成功!", null);
            } else {
                logger.error("撤销人员" + userid + "的登录权限失败!");
                return ResponseData.ERROR("撤销登录权限失败!");
            }
        }
    }

    /**
     * 修改人员登录密码
     * 校验该人员是否具有登录权限,若无,取消修改,若是有,执行修改
     *
     * @param userid   人员唯一标识
     * @param password 人员登录密码
     * @return 是否成功
     */
    @PostMapping("alter")
    @ApiOperation("修改登录密码")
    public ResponseData alterLoginPassword(String userid, String password) {
        /*if (!loginService.haveLoginPrivilege(userid)) {
            logger.error("人员:" + userid + "不具有登录权限,不能修改登录密码!");
            return ResponseData.FAILURE("修改登录密码失败!");
        } else if (!loginService.passwordIsNotNull(password)) {
            logger.error("人员:" + userid + "用户密码为空!");
            return ResponseData.FAILURE("密码格式不正确!");
        } else if (!loginService.passwordLengthIsConformity(password)) {
            logger.error("人员:" + userid + "用户密码长度" + password.length() + "小于6或者大于20!");
            return ResponseData.FAILURE("密码格式不正确!");
        } else {
            if (loginService.modifyLoginPassword(userid, password)) {
                logger.debug("人员:" + userid + "密码:" + password + ",修改成功!");
                return ResponseData.SUCCESS("密码修改成功", null);
            } else {
                logger.error("人员:" + userid + "密码:" + password + ",修改失败!");
                return ResponseData.ERROR("密码修改失败!");
            }
        }*/
        return null;
    }

    /**
     * 重置登录密码
     *
     * @param userid 人员唯一标识
     * @return 是否成功
     */
    @PostMapping("/reset")
    @ApiOperation("重置登录密码")
    public ResponseData resetLoginPassword(String userid) {
        /*if (!loginService.haveLoginPrivilege(userid)) {
            logger.error("人员:" + userid + "不具有登录权限,不能重置登录密码!");
            return ResponseData.FAILURE("重置登录密码失败!");
        } else {
            if (loginService.modifyLoginPassword(userid)) {
                logger.debug("人员:" + userid + "重置登录密码成功!");
                return ResponseData.SUCCESS("重置登录密码成功!", null);
            } else {
                logger.error("人员:" + userid + "不具有登录权限,不能重置登录密码!");
                return ResponseData.ERROR("重置登录密码失败!");
            }
        }*/
        return null;
    }
}
