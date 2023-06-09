package com.e3e4e20.entity.mapper;

/*
Filename: LoginTimeEntity
Created: 2023年05月04日 09时06分09秒 星期四
Author: 天龙梦雪
*/

import java.time.LocalDateTime;

/**
 * 登录时间记录
 * id : id 行标识
 * user_id : userid 人员唯一标识
 * time : loginTime 登录时间
 */
public class LoginTimeEntity {
    private Integer id;
    private String userid;
    private LocalDateTime time;

    public LoginTimeEntity() {
    }

    public LoginTimeEntity(Integer id, String userid, LocalDateTime time) {
        this.id = id;
        this.userid = userid;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LoginTimeEntity{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
