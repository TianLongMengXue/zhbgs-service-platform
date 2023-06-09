package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.UserMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: UserMenuMapper
Created: 2023年04月28日 10时44分09秒 星期五
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface UserMenuMapper {
    /**
     * 根据人员唯一标识检索该人员可操作的全部菜表单项
     *
     * @param userid 人员唯一标识
     * @return 全部菜表单项
     */
    List<String> selectAllItemsByUserId(@Param("userid") String userid);

    /**
     * 根据菜单表项唯一标识检索可操作该表项的全部人员
     *
     * @param menuId 菜单表项唯一标识
     * @return 可操作该表项的全部人员
     */
    List<String> selectAllItemsByMenuId(@Param("menuId") String menuId);

    /**
     * 给指定人员添加可操作的菜单表项
     *
     * @param userMenuEntity 人员唯一标识和菜单表项唯一标识
     * @return 添加成功的记录条数
     */
    Integer insertUserMenu(UserMenuEntity userMenuEntity);

    /**
     * 删除指定人员可操作的全部菜单表项
     *
     * @param userid 人员唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserMenuByUserid(@Param("userid") String userid);

    /**
     * 删除所有人员的指定菜单表项
     *
     * @param menuId 菜单表项的唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserMenuByMenuId(@Param("menuId") String menuId);

    /**
     * 删除指定人员的指定可操作菜单表项
     *
     * @param userMenuEntity 用户唯一标识和菜单表项唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserMenuByUserAndMenuId(UserMenuEntity userMenuEntity);
}
