package com.e3e4e20.service;

import com.e3e4e20.exception.ErrorMessageException;

import java.text.ParseException;

/*
Filename: LoginTimeService
Created: 2023年05月08日 15时54分59秒 星期一
Author: 天龙梦雪
*/
public interface LoginTimeService {
    /**
     * 获取指定人员最近一次的登录时间
     *
     * @param userid 人员唯一标识
     * @return 人员最近一次的登录时间
     */
    String getLastLoginTime(String userid) throws ParseException;

    /**
     * 记录当前人员的登录时间
     *
     * @param userid 人员唯一标识
     * @return true or false
     */
    boolean recordLoginTime(String userid) throws ParseException, ErrorMessageException;

    /**
     * 删除指定人员全部的登录时间
     *
     * @param userid 人员唯一标识
     * @return true or false
     */
    boolean terminateLoginTime(String userid);
}
