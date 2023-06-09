package com.e3e4e20.entity.mapper;

/*
Filename: UserMenuEntity
Created: 2023年04月28日 10时31分39秒 星期五
Author: 天龙梦雪
*/

/**
 * id : id 行唯一标识
 * user_id : userid 用户唯一标识
 * menu_id : menuId 菜单唯一标识
 */
public class UserMenuEntity {
    private Integer id;
    private String userid;
    private String menuId;

    public UserMenuEntity() {
    }

    public UserMenuEntity(Integer id, String userid, String menuId) {
        this.id = id;
        this.userid = userid;
        this.menuId = menuId;
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "UserMenuEntity{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", menuId='" + menuId + '\'' +
                '}';
    }
}
