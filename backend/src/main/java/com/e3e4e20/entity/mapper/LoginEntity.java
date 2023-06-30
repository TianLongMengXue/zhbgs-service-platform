package com.e3e4e20.entity.mapper;

/*
Filename: Login
Created: 2023年04月08日 13时58分15秒 星期六
Author: 天龙梦雪
*/

/**
 * 用户登录
 * id : id 用户唯一标识(雪花算法)
 * username : username 用户名称
 * password : password 用户密码
 * avatar :avatar 人员头像（关联的是文件名称，文件存储的是base64格式和原始图片，传递给前端页面的是base64格式）
 */
//@TableName("t_login")
public class LoginEntity {
//    @TableId("userid")
    private String id;
//    @TableField("username")
    private String username;
//    @TableField("password")
    private String password;
    private String avatar;

    public LoginEntity() {
    }

    public LoginEntity(String id, String username, String password, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "userid='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
