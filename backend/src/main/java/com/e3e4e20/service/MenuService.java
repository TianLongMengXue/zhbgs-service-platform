package com.e3e4e20.service;

import com.e3e4e20.utils.ResponseData;

/*
Filename: MenuService
Created: 2023年04月12日 15时15分30秒 星期三
Author: 天龙梦雪
*/
public interface MenuService {
    /**
     * 获取全部的表单项目
     * @return 全部的表单项目
     */
    ResponseData getAllMenuItem ();

    /**
     * 获取一个具有登录权限的职工所拥有的全部可操作表单项目
     * @param userid 用户唯一标识
     * @return 该用户可操作的表单项目
     */
    ResponseData getAllMenuItemByUserid (String userid);

    /**
     * 添加一个表单项目
     * @param menuEntity 一个表单项目的全新信息
     * @return 添加操作的执行成功与否
     */
//    ResponseData addMenuItem (MenuEntity menuEntity);

    /**
     * 修改表单项目的表单信息
     * @param menuEntity 表项项目的信息
     * @return 修改操作的执行成功与否
     */
//    ResponseData alterMenuItem (MenuEntity menuEntity);

    /**
     * 删除一个表单项目
     * @param uuid 该表单项目的唯一标识
     * @return 删除操作的成功与否
     */
    ResponseData deleteMenuItem (String uuid);
}
