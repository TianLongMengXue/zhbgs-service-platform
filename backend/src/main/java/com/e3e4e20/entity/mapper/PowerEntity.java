package com.e3e4e20.entity.mapper;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/*
Filename: PowerEntity
Created: 2023年04月12日 19时48分40秒 星期三
Author: 天龙梦雪
*/
/*
* 权限表
* id : id 数据表的列唯一标识
* userid : userid 用户唯一标识
* menu_id : menuId 菜单表项唯一标识
* */
//@TableName("t_power")
public class PowerEntity {
    private String id;
//    @TableField("userid")
    private String userid;
//    @TableField("menu_id")
    private String menuId;

    public PowerEntity() {
    }

    public PowerEntity(String id, String userid, String menuId) {
        this.id = id;
        this.userid = userid;
        this.menuId = menuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return "PowerEntity{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", menuId='" + menuId + '\'' +
                '}';
    }
}
