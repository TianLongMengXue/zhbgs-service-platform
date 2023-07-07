package com.e3e4e20.controller;

import com.e3e4e20.utils.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Filename: HomeController
Created: 2023年06月20日 14时52分28秒 星期二
Author: 天龙梦雪
*/
@CrossOrigin
@RestController
@Api(tags = {"404页面管理"}, value = "未定义页面内容控制器")
public class HomeController {
    @RequestMapping("/404")
    public ResponseData index () {
        return ResponseData.NOT_FOUND();
    }
}
