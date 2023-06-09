package com.e3e4e20.entity.service;

/*
Filename: MenuChild
Created: 2023年04月13日 10时15分42秒 星期四
Author: 天龙梦雪
*/
/*
 * 业务页面的菜单栏表单项
 * id : uuid 菜单表项的唯一标识
 * name : name 菜单表项的名称,前端代码的标识符
 * info : info 菜单表项的名称,界面显示的名称
 * url : url 菜单表项的 url ,对应前端的路由
 * icon : icon 菜单表项的图标
 * order : order 菜单表项的显示顺序
 * */
public class MenuChild {
    private String id;
    private String name;
    private String info;
    private String url;
    private String icon;
    private String order;

    public MenuChild() {
    }

    public MenuChild(String id, String name, String info, String url, String icon, String order) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.url = url;
        this.icon = icon;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "MenuChild{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
