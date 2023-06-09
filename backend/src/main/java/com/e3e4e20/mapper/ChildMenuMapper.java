package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.ChildMenuEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: ChildMenuMapper
Created: 2023年04月12日 14时57分15秒 星期三
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface ChildMenuMapper {
    /**
     * 根据父级菜单表项的唯一标识检索全部的子级菜单表项
     * @return 子级菜单表项
     */
    List<ChildMenuEntity> selectAllMenuItemByParentId (@Param("parentId") String parentId);

    /**
     * 根据子级菜单表项的唯一标识查询子级菜单表项的全部信息
     * @param uuid 子级菜单表项的唯一标识
     * @return 子级菜单表项的全部信息
     */
    ChildMenuEntity selectMenuItemById (@Param("id") String uuid);

    /**
     * 添加子级菜单表项
     * @param childMenuEntity 子级菜单表项的全部信息
     * @return 成功添加的子级菜单表项的个数
     */
    int insertMenuItem (ChildMenuEntity childMenuEntity);

    /**
     * 修改子级菜单表项的信息
     * @param childMenuEntity 子级菜单表项的全部信息
     * @return 成功修改的子级菜单表项的个数
     */
    int updateMenuItem (ChildMenuEntity childMenuEntity);

    /**
     * 删除子级菜单表项
     * @param uuid 子级菜单表项的唯一标识
     * @return 成功删除的子级菜单表项的个数
     */
    int deleteMenuItem (@Param("id") String uuid);
}
