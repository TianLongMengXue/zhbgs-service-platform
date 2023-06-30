package com.e3e4e20.entity.mapper;

/*
Filename: ChildMenuEntity
Created: 2023年04月27日 11时29分40秒 星期四
Author: 天龙梦雪
*/

/**
 * 父级菜单表项
 * id : id 菜单表项唯一标识(uuid)
 * name : menuName 菜单表项标识（前端路由标识）
 * info : menuInfo 菜单表项名称（前端菜单页面显示菜单表项名称）
 * url : url 菜单表项路径（前端路由路径）
 * icon : icon 菜单表项图标
 * order : order 菜单表项顺序（前端菜单页面中菜单表项显示的顺序）
 * parent_id : parentId 菜单表项的父级菜单
 */
public class ChildMenuEntity extends RootMenuEntity {
    private String parentId;

    public ChildMenuEntity() {
    }

    public ChildMenuEntity(String uuid, String menuName, String menuInfo, String url, String icon, Integer order, String parentId) {
        super(uuid, menuName, menuInfo, url, icon, order);
        this.parentId = parentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "ChildMenuEntity{" +
                "parentId='" + parentId + '\'' +
                "} " + super.toString();
    }
}
