package com.e3e4e20.constant;

/*
 * Description: 自定义消息返回内容
 * Created: 2020-04-04 21:10 星期六
 * Author: 天龙梦雪
 * */
public class ResultMessage {
    public static final String ID_EMPTY_ERROR = "请输入用户名称!";
    public static final String ID_NULL_ERROR = "用户名称不存在!";
    public static final String WORD_EMPTY_ERROR = "请输入用户密码!";
    public static final String WORD_LENGTH_ERROR = "输入的用户密码长度在 6 到 16 个字符!";
    public static final String WORD_NULL_ERROR = "密码错误!";
    public static final String ID_OR_WORD_ERROR = "该用户不存在或者该用户没有登录权限!";
    public static final String LOGIN_SUCCESS = "登录成功!";
    public static final String UNAUTHENTICATED = "请登录!";
    public static final String UNAUTHORIZED = "权限不足,无法访问!";
    public static final String LOGOUT_SUCCESS = "退出成功!";
    public static final String USER_INFO_NOT_FOUND_NOT_ADD_LOGIN = "不能给非职工成员赋予登录权限!";
    public static final String USER_LOGIN_ALREADY_EXIST = "已经具有登录权限!";
    public static final String USER_LOGIN_NOT_HAVE = "不具有登录权限!";
    public static final String ADD_LOGIN_USER_SUCCESS = "成功赋予登录权限!";
    public static final String ADD_LOGIN_USER_FAILURE = "赋予登录权限失败!";
    public static final String DELETE_LOGIN_USER_SUCCESS = "已撤销登录权限!";
    public static final String DELETE_LOGIN_USER_FAILURE = "撤销登录权限失败!";
    public static final String MODIFY_LOGIN_USER_PASSWORD_SUCCESS = "修改成功!";
    public static final String MODIFY_LOGIN_USER_PASSWORD_FAILURE = "修改失败!";
    public static final String ADD_USER_INFO_SUCCESS = "添加职工信息成功!";
    public static final String ADD_USER_INFO_FAILURE = "添加职工信息失败!";
    public static final String USER_INFO_NOT_FOUND = "不能对一个非职工成员执行操作!";
    public static final String DELETE_USER_INFO_SUCCESS = "删除职工信息成功!";
    public static final String DELETE_USER_INFO_FAILURE = "删除职工信息失败!";
    public static final String MODIFY_USER_INFO_SUCCESS = "修改职工信息成功!";
    public static final String MODIFY_USER_INFO_FAILURE = "删除职工信息失败!";
    public static final String SELECT_USER_INFO_SUCCESS = "查询职工信息成功!";
    public static final String SELECT_MENU_ITEM_SUCCESS = "查询菜单表项成功!";
    public static final String SELECT_MENU_ITEM_FAILURE = "查询菜单表项失败,该菜单表项不存在!";
    public static final String SELECT_MENU_ID_NONE = "不具有除首页以外的权限!";
    public static final String SELECT_MENU_ID_SUCCESS = "查询权限成功!";
    public static final String ADD_MENU_ITEM_SUCCESS = "添加菜单表项成功!";
    public static final String ADD_MENU_ITEM_FAILURE = "添加菜单表项失败!";
    public static final String ALTER_MENU_ITEM_FAILURE = "修改菜单表项失败!";
    public static final String ALTER_MENU_ITEM_SUCCESS = "修改菜单表项成功!";
    public static final String DELETE_MENU_ITEM_FAILURE = "删除菜单表项失败!";
    public static final String DELETE_MENU_ITEM_SUCCESS = "删除菜单表项成功!";
    public static final String CHECK_USER_POWER_FAILURE = "不具有该权限!";
    public static final String CEHCK_USER_POWER_SUCCESS = "具有权限!";
    public static final String GRANT_USER_POWER_SUCCESS = "授予权限成功!";
    public static final String GRANT_USER_POWER_FAILURE = "授予权限失败!";
    public static final String ANNUL_USER_POWER_SUCCESS = "撤销权限成功!";
    public static final String ANNUL_USER_POWER_FAILURE = "撤销权限失败!";
}
