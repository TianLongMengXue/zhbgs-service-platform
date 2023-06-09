package com.e3e4e20.entity.mapper;

/*
Filename: RootMenuEntity
Created: 2023年04月27日 11时25分10秒 星期四
Author: 天龙梦雪
*/

/**
 * 父级菜单表项
 * id : uuid 菜单表项唯一标识
 * name : menuName 菜单表项标识（前端路由标识）
 * info : menuInfo 菜单表项名称（前端菜单页面显示菜单表项名称）
 * url : url 菜单表项路径（前端路由路径）
 * icon : icon 菜单表项图标
 * order : order 菜单表项顺序（前端菜单页面中菜单表项显示的顺序）
 */
public class RootMenuEntity {
    private String uuid;
    private String menuName;
    private String menuInfo;
    private String url;
    private String icon;
    private Integer order;

    public RootMenuEntity() {
    }

    public RootMenuEntity(String uuid, String menuName, String menuInfo, String url, String icon, Integer order) {
        this.uuid = uuid;
        this.menuName = menuName;
        this.menuInfo = menuInfo;
        this.url = url;
        this.icon = icon;
        this.order = order;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuInfo() {
        return menuInfo;
    }

    public void setMenuInfo(String menuInfo) {
        this.menuInfo = menuInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "RootMenuEntity{" +
                "uuid='" + uuid + '\'' +
                ", menuName='" + menuName + '\'' +
                ", menuInfo='" + menuInfo + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", order=" + order +
                '}';
    }
}
