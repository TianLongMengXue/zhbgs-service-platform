package com.e3e4e20.entity.mapper;

/*
Filename: ApiEntity
Created: 2023年04月28日 16时15分00秒 星期五
Author: 天龙梦雪
*/

/**
 * id : id api接口唯一标识(uuid)
 * api : api api接口
 * is_admin : isAdmin 系统管理员专属权限,1为true,0为false
 * name : name api接口标识
 * info : info api接口名称（前端页面显示的内容）
 */
public class ApiEntity {
    private String id;
    private String api;
    private Integer isAdmin;
    private String name;
    private String info;

    public ApiEntity() {
    }

    public ApiEntity(String id, String api, Integer isAdmin, String name, String info) {
        this.id = id;
        this.api = api;
        this.isAdmin = isAdmin;
        this.name = name;
        this.info = info;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "ApiEntity{" +
                "uuid='" + id + '\'' +
                ", api='" + api + '\'' +
                ", isAdmin=" + isAdmin +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
