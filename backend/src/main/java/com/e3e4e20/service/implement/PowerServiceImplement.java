package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.PowerEntity;
import com.e3e4e20.mapper.PowerMapper;
import com.e3e4e20.service.PowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
Filename: PowerServiceImplement
Created: 2023年04月17日 10时53分24秒 星期一
Author: 天龙梦雪
*/
@Service(value = "powerService")
public class PowerServiceImplement implements PowerService {
    @Resource(type = PowerMapper.class)
    private PowerMapper powerMapper;
    private final Logger log = LoggerFactory.getLogger("Class:PowerServiceImplement");

    /**
     * 1、检索数据库中是否具有该用户将要操作的表单项
     * 2、若是数据库返回结果 powerEntity 中有一项是 null 就说明该用户不具有权限操作
     * @param powerEntity 用户唯一标识和菜单表项唯一标识
     * @return true or false
     */
    @Override
    public boolean checkPower(PowerEntity powerEntity) {

        try {
            PowerEntity power = powerMapper.selectByMenuIdAndUserid(powerEntity);
            return null != power.getUserid() && null != power.getMenuId();
        } catch (Exception e) {
            log.info(e.toString());
            return false;
        }
    }

    /**
     * 为用户授予权限
     * 1、检索数据库中该用户是否已经具有该表项的操作权限
     * 2、若是没有则为该用户授予权限,若是已具有权限,则报错
     * @param powerEntity 用户唯一标识和菜单表项唯一标识
     * @return true or false
     */
    @Override
    public boolean grantPower(PowerEntity powerEntity) {
        try {
            PowerEntity power = powerMapper.selectByMenuIdAndUserid(powerEntity);
            if (null == power.getUserid() && null == power.getMenuId()) {
                return 1 == powerMapper.addMenuIdAndUserid(powerEntity);
            } else {
                return false;
            }
        } catch (Exception e) {
            log.info(e.toString());
            return false;
        }
    }

    /**
     * 1、校验改用是否已经被授予该权限
     * 2、若是该用户已被授予该权限,那么就直接撤销改用的权限
     * 3、若是该用户没有被授予该权限,那么直接报错
     * @param powerEntity 用户唯一标识和菜单表项唯一标识
     * @return true or false
     */
    @Override
    public boolean annulPower(PowerEntity powerEntity) {
        try {
            PowerEntity power = powerMapper.selectByMenuIdAndUserid(powerEntity);
            if (null != power.getUserid() && null != power.getMenuId()) {
                return 1 == powerMapper.deleteMenuIdByUserid(powerEntity.getUserid());
            } else {
                return false;
            }
        } catch (Exception e) {
            log.info(e.toString());
            return false;
        }
    }
}
