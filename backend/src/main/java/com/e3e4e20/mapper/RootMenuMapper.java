package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.RootMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: RootMenuMapper
Created: 2023年04月27日 11时24分33秒 星期四
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface RootMenuMapper {
    /**
     * 检索全部的父级菜单表项
     *
     * @return 全部的父级菜单表项
     */
    List<RootMenuEntity> selectAllItem();

    /**
     * 根据父级菜单的唯一标识检索父级菜单表项信息
     *
     * @param uuid 父级菜单的唯一标识
     * @return 父级菜单表项信息
     */
    RootMenuEntity selectItemById(@Param("id") String uuid);

    /**
     * 添加父级菜单表项
     *
     * @param rootMenuEntity 父级菜单表项的全部信息
     * @return 添加成功的记录条数
     */
    Integer insertMenu(RootMenuEntity rootMenuEntity);

    /**
     * 修改父级菜单表项信息
     *
     * @param rootMenuEntity 父级菜单表项的信息
     * @return 修改成功的记录条数
     */
    Integer updateMenu(RootMenuEntity rootMenuEntity);

    /**
     * 删除父级菜单表项
     *
     * @param uuid 父级菜单表项的唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteMenu(@Param("id") String uuid);
}
