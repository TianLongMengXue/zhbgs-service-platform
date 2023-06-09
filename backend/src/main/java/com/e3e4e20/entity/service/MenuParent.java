package com.e3e4e20.entity.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
Filename: MenuParent
Created: 2023年04月13日 10时15分25秒 星期四
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
 * child : child 子级菜单
 * */
public class MenuParent extends MenuChild{
    private CopyOnWriteArrayList<MenuChild> childList;

    public MenuParent() {
    }

    public MenuParent(String id, String name, String info, String url, String icon, String order, CopyOnWriteArrayList<MenuChild> childList) {
        super(id, name, info, url, icon, order);
        this.childList = childList;
    }

    public List<MenuChild> getChildList() {
        return childList;
    }

    public void setChildList(CopyOnWriteArrayList<MenuChild> childList) {
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "MenuParent{" +
                "childList=" + childList +
                "} " + super.toString();
    }
}
