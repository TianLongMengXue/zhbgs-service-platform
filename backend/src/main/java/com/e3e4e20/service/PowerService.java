package com.e3e4e20.service;

import com.e3e4e20.entity.mapper.PowerEntity;

/*
Filename: PowerService
Created: 2023年04月17日 10时31分44秒 星期一
Author: 天龙梦雪
*/
public interface PowerService {
    /**
     * 校验改用户是否具有操作该表项的权限
     * @param powerEntity 用户唯一标识和菜单表项唯一标识
     * @return true or false
     */
    boolean checkPower (PowerEntity powerEntity);

    /**
     * 为用户授予权限
     * @param powerEntity 用户唯一标识和菜单表项唯一标识
     * @return true or false
     */
    boolean grantPower (PowerEntity powerEntity);

    /**
     * 撤销用户的权限
     * @param powerEntity 用户唯一标识和菜单表项唯一标识
     * @return 撤销权限是否成功
     */
    boolean annulPower (PowerEntity powerEntity);
}
