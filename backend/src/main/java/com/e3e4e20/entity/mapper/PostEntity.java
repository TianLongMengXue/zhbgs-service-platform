package com.e3e4e20.entity.mapper;

/*
Filename: PostEntity
Created: 2023年04月25日 11时22分41秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户岗位名称
 * id : uuid 岗位名称唯一标识
 * name : postName 岗位名称
 */
public class PostEntity {
    private String uuid;
    private String postName;

    public PostEntity(String uuid, String postName) {
        this.uuid = uuid;
        this.postName = postName;
    }

    public PostEntity() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "uuid='" + uuid + '\'' +
                ", postName='" + postName + '\'' +
                '}';
    }
}
