package com.e3e4e20.entity.mapper;

/*
Filename: UserApiEntity
Created: 2023年04月28日 10时38分53秒 星期五
Author: 天龙梦雪
*/

/**
 * id : id 行唯一标识
 * user_id : userid 用户唯一标识
 * api_id : apiId api唯一标识
 */
public class UserApiEntity {
    private Integer id;
    private String userid;
    private String apiId;

    public UserApiEntity() {
    }

    public UserApiEntity(Integer id, String userid, String apiId) {
        this.id = id;
        this.userid = userid;
        this.apiId = apiId;
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

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    @Override
    public String toString() {
        return "UserApiEntity{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", apiId='" + apiId + '\'' +
                '}';
    }
}
