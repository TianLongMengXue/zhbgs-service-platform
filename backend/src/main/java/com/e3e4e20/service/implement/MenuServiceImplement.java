package com.e3e4e20.service.implement;

import com.e3e4e20.constant.ResultMessage;
import com.e3e4e20.mapper.ChildMenuMapper;
import com.e3e4e20.mapper.PowerMapper;
import com.e3e4e20.mapper.RootMenuMapper;
import com.e3e4e20.service.MenuService;
import com.e3e4e20.entity.service.MenuChild;
import com.e3e4e20.entity.service.MenuParent;
import com.e3e4e20.utils.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/*
Filename: MenuServiceImplement
Created: 2023年04月12日 15时34分06秒 星期三
Author: 天龙梦雪
*/
@Service(value = "menuService")
public class MenuServiceImplement implements MenuService {
    @Resource(type = RootMenuMapper.class)
    private RootMenuMapper rootMenuMapper;
    @Resource(type = ChildMenuMapper.class)
    private ChildMenuMapper childMenuMapper;
    @Resource(type = PowerMapper.class)
    private PowerMapper powerMapper;
    private final Logger logger = LoggerFactory.getLogger("Class:MenuServiceImplement");

    @Override
    public ResponseData getAllMenuItem() {
        /*try {
            List<MenuEntity> menuEntityList = rootMenuMapper.selectAllMenuItem();
            logger.info("getAllMenuItem"+menuEntityList);
            List<MenuParent> menuList = getMenuList(menuEntityList);
            logger.info("getAllMenuItem"+menuList);
            return ResponseData.SUCCESS(ResultMessage.SELECT_MENU_ITEM_SUCCESS, menuList);
        } catch (Exception e) {
            logger.info(e.toString());
            return ResponseData.ERROR(ResultMessage.SELECT_MENU_ITEM_FAILURE);
        }*/
        return null;
    }

    @Override
    public ResponseData getAllMenuItemByUserid(String userid) {
        /*try {
            List<String> menuIdList = powerMapper.selectMenuIdByUserid(userid);
            List<MenuEntity> menuEntityList = new ArrayList<>();
            for (String item : menuIdList) {
                menuEntityList.add(rootMenuMapper.selectMenuItemById(item));
            }
            List<MenuParent> menuList = getMenuList(menuEntityList);
            return ResponseData.SUCCESS(ResultMessage.SELECT_MENU_ITEM_SUCCESS, menuList);
        } catch (Exception e) {
            logger.info(e.toString());
            // 说明该用户除了首页没有其他可以操作的菜单表项
            return ResponseData.ERROR(ResultMessage.SELECT_MENU_ID_NONE);
        }*/
        return null;
    }

    /*@Override*/
    public ResponseData addMenuItem(/*MenuEntity menuEntity*/) {
        /*
         * 这里默认添加的菜单表项都是不重复的,不做重复校验
         * */
        /*try {
            if (1 == rootMenuMapper.insertMenuItem(menuEntity)) {
                return ResponseData.SUCCESS(ResultMessage.ADD_MENU_ITEM_SUCCESS, null);
            } else {
                return ResponseData.ERROR(ResultMessage.ADD_MENU_ITEM_FAILURE);
            }
        } catch (Exception e) {
            logger.info(e.toString());
            return ResponseData.ERROR(ResultMessage.ADD_MENU_ITEM_FAILURE);
        }*/
        return null;
    }

    /*@Override*/
    public ResponseData alterMenuItem(/*MenuEntity menuEntity*/) {
        /*
         * 1、检索该菜单表项是否存在
         * 2、若存在修改该菜单表项,若不存在直接报错
         * */
        /*try {
            rootMenuMapper.selectMenuItemById(menuEntity.getUuid());
        } catch (Exception e) {
            logger.info(e.toString());
            return ResponseData.ERROR(ResultMessage.SELECT_MENU_ITEM_FAILURE);
        }
        try {
            if (1 == rootMenuMapper.insertMenuItem(menuEntity)) {
                return ResponseData.SUCCESS(ResultMessage.ALTER_MENU_ITEM_SUCCESS, null);
            } else {
                return ResponseData.ERROR(ResultMessage.ALTER_MENU_ITEM_FAILURE);
            }
        } catch (Exception e) {
            logger.info(e.toString());
            return ResponseData.ERROR(ResultMessage.ALTER_MENU_ITEM_FAILURE);
        }*/
        return null;
    }

    @Override
    public ResponseData deleteMenuItem(String uuid) {
        /*
         * 1、检索数据库中是否存在该菜单表项
         * 2、若是存在,检索数据库中是否有用户具有该菜单表项的操作权限,若是有则撤销该用户对该菜单表项的权限
         * 3、删除数据库中该菜单表项的信息
         * */
        /*try {
            rootMenuMapper.selectMenuItemById(uuid);
        } catch (Exception e) {
            logger.info(e.toString());
            return ResponseData.ERROR(ResultMessage.SELECT_MENU_ITEM_FAILURE);
        }
        try {
            powerMapper.deleteAllUseridByMenuId(uuid);
        } catch (Exception e) {
            logger.info(e.toString());
            return ResponseData.ERROR(ResultMessage.DELETE_MENU_ITEM_FAILURE);
        }
        try {
            if (1 == rootMenuMapper.deleteMenuItem(uuid)) {
                return ResponseData.SUCCESS(ResultMessage.DELETE_MENU_ITEM_SUCCESS, null);
            } else {
                return ResponseData.ERROR(ResultMessage.DELETE_MENU_ITEM_FAILURE);
            }
        } catch (Exception e) {
            logger.info(e.toString());
            return ResponseData.ERROR(ResultMessage.DELETE_MENU_ITEM_FAILURE);
        }*/
        return null;
    }

    private List<MenuParent> getMenuList(/*List<MenuEntity> menuEntityList*/) {
        /*List<Map<String, Object>> menuList = new ArrayList<>();
        // 第一次遍历全部的菜单表项,将所有的父级菜单表项进行存储
        for (MenuEntity menuEntity : menuEntityList) {
            if (menuEntity.getParentId().equals("0")) {
                Map<String, Object> parentMenuItem = new HashMap<>();
                parentMenuItem.put("id", menuEntity.getUuid());
                parentMenuItem.put("name", menuEntity.getName());
                parentMenuItem.put("info", menuEntity.getInfo());
                parentMenuItem.put("url", menuEntity.getUrl());
                parentMenuItem.put("icon", menuEntity.getIcon());
                parentMenuItem.put("order", menuEntity.getOrder());
                List<Map<String, Object>> childMenuItemList = new ArrayList<>();
                parentMenuItem.put("child", childMenuItemList);
                menuList.add(parentMenuItem);
            }
        }
        // 第二次遍历全部的菜单表项,将所有的子菜单表项进行存储
        for (MenuEntity menuEntity : menuEntityList) {
            if (!menuEntity.getParentId().equals("0")) {
                for (Map<String, Object> parentMenuItem : menuList) {
                    if (menuEntity.getParentId().equals(parentMenuItem.get("id"))) {
                        List<Map<String, Object>> childMenuItemList = (List<Map<String, Object>>) parentMenuItem.get("child");
                        Map<String, Object> childMenuItem = new HashMap<>();
                        childMenuItem.put("id", menuEntity.getUuid());
                        childMenuItem.put("name", menuEntity.getName());
                        childMenuItem.put("info", menuEntity.getInfo());
                        childMenuItem.put("url", menuEntity.getUrl());
                        childMenuItem.put("icon", menuEntity.getIcon());
                        childMenuItem.put("order", menuEntity.getOrder());
                        childMenuItemList.add(childMenuItem);
                    }
                }
            }
        }*/
        /*CopyOnWriteArrayList<MenuParent> menuList = new CopyOnWriteArrayList<>();
        CopyOnWriteArrayList<MenuEntity> menuEntities = new CopyOnWriteArrayList<>(menuEntityList);
        // 第一次遍历全部的菜单表项,将所有的父级菜单表项进行存储
        for (MenuEntity menuEntity : menuEntities) {
            // 父级菜单的 parentId 属性为 0
            logger.info("getMenuList 173"+menuEntity.toString());
            if (menuEntity.getParentId().equals("0")) {
                MenuParent menuParent = new MenuParent();
                menuParent.setId(menuEntity.getUuid());
                menuParent.setName(menuEntity.getName());
                menuParent.setInfo(menuEntity.getInfo());
                menuParent.setIcon(menuEntity.getIcon());
                menuParent.setUrl(menuEntity.getUrl());
                menuParent.setOrder(menuEntity.getOrder());
                logger.info("getMenuList 182"+menuParent);
                menuList.add(menuParent);
                logger.info("getMenuList 183"+menuList);
                menuEntities.remove(menuEntity);
                logger.info("getMenuList 185"+menuEntities.toString());
            }
        }
        // 第二次遍历全部的菜单表项,将所有的子菜单表项进行存储
        for (MenuEntity menuEntity : menuEntities) {
            logger.info("getMenuList 192"+menuEntity);
            for (MenuParent menuParent : menuList) {
                logger.info("getMenuList 194"+menuParent);
                if (menuEntity.getParentId().equals(menuParent.getId())) {
                    MenuChild menuChild = new MenuChild();
                    menuChild.setId(menuEntity.getUuid());
                    menuChild.setName(menuEntity.getName());
                    menuChild.setInfo(menuEntity.getInfo());
                    menuChild.setIcon(menuEntity.getIcon());
                    menuChild.setOrder(menuEntity.getOrder());
                    menuChild.setUrl(menuEntity.getUrl());
                    logger.info("getMenuList 203"+menuChild);
                    *//*if (menuParent.getChildList().isEmpty()) {
                        CopyOnWriteArrayList<MenuChild> childList = new CopyOnWriteArrayList<>();
                        childList.add(menuChild);
                        menuParent.setChildList(childList);
                    } else {
                        menuParent.getChildList().add(menuChild);
                    }*//*
                    try {
                        menuParent.getChildList().add(menuChild);
                    } catch (Exception e) {
                        logger.info(e.toString());
                        CopyOnWriteArrayList<MenuChild> childList = new CopyOnWriteArrayList<>();
                        childList.add(menuChild);
                        menuParent.setChildList(childList);
                    }
                    logger.info("getMenuList 205"+menuParent);
                }
            }
        }
        logger.info("getAllMenuItem: " + menuList);
        return menuList;*/
        return null;
    }
}
