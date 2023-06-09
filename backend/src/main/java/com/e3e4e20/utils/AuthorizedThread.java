package com.e3e4e20.utils;

import java.util.Map;

/*
Filename: AuthorizedThread
Created: 2023年06月02日 10时46分55秒 星期五
Author: 天龙梦雪
*/
public class AuthorizedThread {
    public static final ThreadLocal<Map<String,Object>> authorizedThread = new ThreadLocal<>();

    public static void setAuthorizedThread(Map<String,Object> userBaseInfo) {
        authorizedThread.set(userBaseInfo);
    }

    public static Map<String,Object> getAuthorizedThread() {
        return authorizedThread.get();
    }

    public static void removeAuthorizedThread() {
        authorizedThread.remove();
    }
}
