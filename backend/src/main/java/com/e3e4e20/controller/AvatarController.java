package com.e3e4e20.controller;

import com.e3e4e20.constant.ProjectDefaultConfig;
import com.e3e4e20.exception.FailureMessageException;
import com.e3e4e20.service.LoginService;
import com.e3e4e20.service.implement.LoginServiceImplement;
import com.e3e4e20.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
Filename: AvatarController
Created: 2023年07月07日 11时12分02秒 星期五
Author: 天龙梦雪
*/
@CrossOrigin
@RestController
@RequestMapping("/avatar")
@Api(tags = {"人员头像管理"}, value = "人员头像控制器")
public class AvatarController {
    private final Logger log = LoggerFactory.getLogger("Class: AvatarController ");
    @Resource(name = "loginService")
    private final LoginService loginService = new LoginServiceImplement();

    @PostMapping("/get")
    @ApiOperation("获取人员头像")
    public ResponseData getAvatar() {
        String userid = (String) AuthorizedThread.getAuthorizedThread().get("id");
        log.info("getAvatar: 正在获取人员: " + userid + " 的头像...");
        String avatarName = loginService.getUserAvatar(userid);
        log.info("getAvatar: 人员 "+userid+" 的头像文件名称为 "+avatarName);
        String imageSrcUrl =
                new LocalFileOperation(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH+avatarName).getFileSrcUrl();
        Map<String,String> result = new HashMap<>();
        result.put("avatar", imageSrcUrl);
        return ResponseData.SUCCESS("获取人员头像成功!", result);
    }

    @PostMapping("/alter")
    @ApiOperation("修改人员头像")
    @ApiImplicitParam(name = "avatar", value = "人员上传的头像图片文件")
    public ResponseData alterAvatar(
            @RequestParam(value = "avatar", required = true) MultipartFile avatar
    ) throws IOException {
        String userid = (String) AuthorizedThread.getAuthorizedThread().get("id");
        log.info("alterAvatar: 正在修改人员: " + userid + "的头像...");
        // 若是没有上传图片作为头像,直接报错
        if (null == avatar) {
            log.error("alterAvatar: 用户: " + userid + " 没有上传头像文件,不能修改头像!");
            throw new FailureMessageException("没有上传头像文件,不能修改用户头像!");
        }
        // 为该文件生成一个文件名称
        String avatarName = new Uuid().createUuid();
        // 判断头像文件是否是一个图片文件
        MultipartFileOperation multipartFileOperation = new MultipartFileOperation(avatar,
                ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH, avatarName);
        multipartFileOperation.isImageFile();
        // 保存文件
        multipartFileOperation.saveFile();
        // 将头像文件相关索引保存到数据库
        loginService.modifyUserAvatar(userid, multipartFileOperation.getFileName());
        // 将头像文件生成base64格式返回给前端
        LocalFileOperation localFileOperation = new LocalFileOperation(multipartFileOperation.getFileAbsolutePath());
        String imageSrcUrl = localFileOperation.getFileSrcUrl();
        Map<String, String> result = new HashMap<>();
        result.put("avatar", imageSrcUrl);
        log.info("alterAvatar: 修改人员 " + userid + " 的头像成功!");
        return ResponseData.SUCCESS("修改人员头像成功!", result);
    }
}
