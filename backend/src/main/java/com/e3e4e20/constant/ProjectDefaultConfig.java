package com.e3e4e20.constant;

/*
Filename: ProjectDefaultConfig
Created: 2023年04月11日 12时51分31秒 星期二
Author: 天龙梦雪
*/
public class ProjectDefaultConfig {
    public static final String PROJECT_NAME = "综合办公室业务平台";
    public static final String PROJECT_DESCRIPTION = "综合办公室业务平台";
    public static final String SWAGGER_API_BASE_PACKAGE = "com.e3e4e20";
    public static final String SWAGGER_API_DESCRIPTION = "综合办公室业务平台API文档";
    public static final String SWAGGER_API_VERSION = "1.0";
    // 设置 token 的默认有效时间为 1 天 (24*60*60=86400),这里使用的是秒
    public static final long TOKEN_EXPIRE_DAY = 86400;
    // 若是用户选择在前端保存用户信息,则 token 的有效时间为 30 天(30*24*60*60=2592000)
    public static final long TOKEN_EXPIRE_MONTH = 2592000;
    // 秘钥
    // Whatever doesn't bring you down makes you stronger
    // What does not defeat you makes you stronger
    public static final String  TOKEN_SECRET= "whatdoesnotdefeatyoumakesyoustronger";
    // 为职工赋予登录权限之后,默认的登录密码
    public static final String PROJECT_DEFAULT_PASSWORD = "123456";
    // 登录密码的最短长度
    public static final Integer MIN_PASSWORD_LENGTH = 6;
    // 登录密码的最长长度
    public static final Integer MAX_PASSWORD_LENGTH = 20;
    // 登陆之后,在首页显示个人信息时,展示的用户头像,使用的图片的路径
    public static final String PROJECT_DEFAULT_AVATAR_PATH = "C:/Archive/Pictures/";
    public static final String PROJECT_DEFAULT_AVATAR_NAME = "login.ico";
}
