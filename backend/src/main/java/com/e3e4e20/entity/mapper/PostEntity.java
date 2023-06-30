package com.e3e4e20.entity.mapper;

/*
Filename: PostEntity
Created: 2023年04月25日 11时22分41秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户岗位名称
 * id : id 岗位名称唯一标识(uuid)
 * name : postName 岗位名称
 */
public class PostEntity {
    private String id;
    private String postName;

    public PostEntity(String id, String postName) {
        this.id = id;
        this.postName = postName;
    }

    public PostEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "uuid='" + id + '\'' +
                ", postName='" + postName + '\'' +
                '}';
    }
}
