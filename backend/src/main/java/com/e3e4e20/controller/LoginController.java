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
import com.e3e4e20.utils.AuthorizedThread;
import com.e3e4e20.utils.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * @param username 用户名称-username
     * @param password 用户密码-password
     * @param isStore  保存登录信息-isStore
     * @return token userid 或者登录信息有误
     */
    @PostMapping("/check")
    @ApiOperation("根据人员的用户名称和用户密码执行登录校验")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称"),
            @ApiImplicitParam(name = "password", value = "用户密码"),
            @ApiImplicitParam(name = "store", value = "是否保存用户登录信息,若是保存token有效期为30天,否则token仅当前登录有效"),
    })
    public ResponseData getToken(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "store", defaultValue = "false", required = false) boolean isStore
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
        logger.debug("getToken: 用户唯一标识:" + userid + ",用户名称:" + username + ",token为" + token);
        // 封装后端响应给前端的数据
        Map<String, String> result = new HashMap<String, String>();
        result.put("token", token);
        result.put("id", userid);
        // 记录人员登录的时间
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
    @PostMapping( "/grant")
    @ApiOperation("根据人员唯一标识授予登录权限")
    @ApiImplicitParam(name = "id", value = "人员唯一标识")
    public ResponseData grantLoginPrivilege(@RequestParam(value = "id", required = true) String userid) throws ParseException,
            ErrorMessageException {
        logger.debug("grantLoginPrivilege: 正在为人员: " + userid + ",授予登录权限");
        if (loginService.haveLoginPrivilege(userid)) { // 该人员已经具有登录权限,不能重复授予
            logger.error("grantLoginPrivilege: 人员唯一标识=" + userid + ",已具有登录权限,不能重复授予!");
            throw new ErrorMessageException("授予登录权限失败!");
        }
        if (!userInfoService.userIsNotNull(userid)) { // 系统里没有该人员的基本信息,不能授予登录权限
            logger.error("grantLoginPrivilege: 人员" + userid + ",没有该人员的基本信息,不能授予登录权限");
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
        loginEntity.setUsername(username);
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
        logger.debug("annulLoginPrivilege: 正在为人员: " + userid + ",撤销登录权限!");
        if (!loginService.haveLoginPrivilege(userid)) {
            logger.error("annulLoginPrivilege: 人员:" + userid + "不具有登录权限,不能撤销其登录权限!");
            throw new ErrorMessageException("撤销登录权限失败!");
        } else {
            if (!loginTimeService.terminateLoginTime(userid)) {
                logger.debug("annulLoginPrivilege: 正在删除人员信息,但是该人员:" + userid + "具有登录权限,且删除登录时间记录失败!");
                // return ResponseData.FAILURE("删除人员信息失败!");
            }
            if (loginService.deleteLoginUser(userid)) {
                logger.debug("annulLoginPrivilege: 撤销人员" + userid + "的登录权限成功!");
                return ResponseData.SUCCESS("撤销登录权限成功!", null);
            } else {
                logger.error("annulLoginPrivilege: 撤销人员" + userid + "的登录权限失败!");
                return ResponseData.ERROR("撤销登录权限失败!");
            }
        }
    }

    /**
     * 修改人员登录密码
     * 校验该人员是否具有登录权限,若无,取消修改,若是有,执行修改
     *
     * @param passwordOld 旧的登陆密码
     * @param passwordNew 新的登录密码
     * @return 是否成功
     */
    @PostMapping("alter")
    @ApiOperation("修改登录密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "old", value = "旧的登陆密码"),
            @ApiImplicitParam(name = "new", value = "新的登录密码")
    })
    public ResponseData alterLoginPassword(
            @RequestParam(value = "old", required = true) String passwordOld,
            @RequestParam(value = "new", required = true) String passwordNew
    ) throws ErrorMessageException {
        String userid = (String) AuthorizedThread.getAuthorizedThread().get("userid");
        // 检验当前人员是否具有登录权限
        if (!loginService.haveLoginPrivilege(userid)) {
            logger.error("alterLoginPassword: 人员:" + userid + "不具有登录权限,不能修改登录密码!");
            return ResponseData.FAILURE("修改登录密码失败!");
        }
        // 校验旧的登录密码格式是否有误
        if (passwordOld == null || passwordOld.length() <= 0) {
            logger.error("alterLoginPassword: 人员：" + userid + ",旧的登陆密码为空,不能修改登录密码!");
            return ResponseData.FAILURE("旧的登陆密码不正确!");
        }
        if (passwordOld.length() < ProjectDefaultConfig.MIN_PASSWORD_LENGTH) {
            logger.error("alterLoginPassword: password is too short,userid=" + userid + "的旧登录密码长度: " + passwordOld.length() + ",小于最短长度" + ProjectDefaultConfig.MIN_PASSWORD_LENGTH + "!");
            return ResponseData.FAILURE("旧的登陆密码不正确!");
        }
        if (passwordOld.length() > ProjectDefaultConfig.MAX_PASSWORD_LENGTH) {
            logger.error("alterLoginPassword: password is too long,userid=" + userid + "的旧登录密码长度: " + passwordOld.length() + ",大于最大长度" + ProjectDefaultConfig.MAX_PASSWORD_LENGTH + "!");
            return ResponseData.FAILURE("旧的登陆密码不正确!");
        }
        // 检验旧的登陆密码是否正确
        String passwordOldEncoder = new CommonsCodecConfig().sha512HexEncode(passwordOld);
        if (loginService.haveLoginPrivilege(userid, passwordOldEncoder) == null) {
            logger.error("alterLoginPassword: 人员:" + userid + ",输入的旧登陆密码有误!");
            return ResponseData.FAILURE("旧的登陆密码不正确!");
        }
        // 校验新的登录密码格式是否正确
        if (passwordNew == null || passwordNew.length() <= 0) {
            logger.error("alterLoginPassword: 人员：" + userid + ",新的登陆密码为空,不能修改登录密码!");
            return ResponseData.FAILURE("新的登陆密码不能为空!");
        }
        if (passwordNew.length() < ProjectDefaultConfig.MIN_PASSWORD_LENGTH) {
            logger.error("alterLoginPassword: password is too short,userid=" + userid + "的新登录密码长度: " + passwordNew.length() + ",小于最短长度" + ProjectDefaultConfig.MIN_PASSWORD_LENGTH + "!");
            return ResponseData.FAILURE("新的登陆密码长度不符合!");
        }
        if (passwordNew.length() > ProjectDefaultConfig.MAX_PASSWORD_LENGTH) {
            logger.error("alterLoginPassword: password is too long,userid=" + userid + "的新登录密码长度: " + passwordNew.length() + ",大于最大长度" + ProjectDefaultConfig.MAX_PASSWORD_LENGTH + "!");
            return ResponseData.FAILURE("新的登陆密码长度不符合!");
        }
        // 若是新、旧登录密码相同,不用修改数据库内信息
        if (passwordOld.equals(passwordNew)) {
            logger.error("alterLoginPassword: 人员:" + userid + ",输入的新、旧登录密码相同,无需修改数据库内信息!");
            return ResponseData.SUCCESS("密码修改成功!", null);
        }
        // 修改数据库内登录密码
        String passwordNewEncoder = new CommonsCodecConfig().sha512HexEncode(passwordNew);
        if (loginService.modifyLoginPassword(userid, passwordNewEncoder)) {
            logger.debug("alterLoginPassword: 人员:" + userid + ",密码修改成功!");
            return ResponseData.SUCCESS("密码修改成功", null);
        } else {
            logger.error("alterLoginPassword: 人员:" + userid + ",密码修改失败!");
            return ResponseData.ERROR("密码修改失败!");
        }
    }

    /**
     * 重置登录密码
     *
     * @param userid 人员唯一标识
     * @return 是否成功
     */
    @PostMapping("/reset")
    @ApiOperation("重置登录密码")
    @ApiImplicitParam(name = "id", value = "提供了用户id的重置指定id的用户密码,没有提供id的重置当前登录人员的登录密码")
    public ResponseData resetLoginPassword(@RequestParam(value = "id", required = false) String userid) throws ErrorMessageException {
        // 若是没有指定需要重置登录密码的人员,则重置当前登录人员的登录密码
        if (null == userid) {
            userid = (String) AuthorizedThread.getAuthorizedThread().get("userid");
        }
        // 校验指定的人员是否具有登录权限
        if (!loginService.haveLoginPrivilege(userid)) {
            logger.error("resetLoginPassword: 人员: " + userid + ",不具有登录权限,不能重置登录密码!");
            return ResponseData.FAILURE("重置登录密码失败!");
        }
        // 修改指定人员的登录密码
        String defaultPasswordEncoder = new CommonsCodecConfig().sha512HexEncode(ProjectDefaultConfig.PROJECT_DEFAULT_PASSWORD);
        if (loginService.modifyLoginPassword(userid, defaultPasswordEncoder)) {
            logger.debug("resetLoginPassword: 人员: " + userid + ",重置登录密码成功!");
            return ResponseData.SUCCESS("重置登录密码成功!", null);
        } else {
            logger.error("resetLoginPassword: 人员:" + userid + "重置登录密码失败!");
            return ResponseData.ERROR("重置登录密码失败!");
        }
    }
}
