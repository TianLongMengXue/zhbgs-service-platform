package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.PowerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: PowerMapper
Created: 2023年04月12日 19时52分35秒 星期三
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface PowerMapper {
    /**
     * 根据用户唯一标识查询该用户有权限操作的菜单表项
     * @param userid 用户唯一标识
     * @return 该用户有权限操作的全部菜单表项
     */
//    @Select("select menu_id from t_power where userid=#{userid}")
    List<String> selectMenuIdByUserid (String userid);

    /**
     * 根据用户唯一标识和菜单表项唯一标识查询数据库中是否存在该记录
     * @param powerEntity  用户唯一标识和菜单表项唯一标识
     * @return 用户唯一标识和菜单表项唯一标识
     */
    PowerEntity selectByMenuIdAndUserid(PowerEntity powerEntity);

    /**
     * 为用户添加可操作性的菜单表项
     * @param powerEntity 用户唯一标识和菜单表项的唯一标识
     * @return 添加的可操作菜单表项的个数
     */
//    @Insert("insert into t_power (userid,menu_id) values(#{userid},#{menuId})")
    int addMenuIdAndUserid (PowerEntity powerEntity);

    /**
     * 删除该用户的全部的可操作的菜单表项
     * @param userid 用户唯一标识
     * @return 删除的菜单表项的个数
     */
//    @Delete("delete from t_power where userid=#{userid}")
    int deleteMenuIdByUserid (String userid);

    /**
     * 删除指定用户的指定可操作菜单表项
     * @param powerEntity 用户唯一标识和菜单表项唯一标识
     * @return 删除的菜单表项的个数
     */
//    @Delete("delete from t_power where menu_id=#{menuId} and userid=#{userid}")
    int deleteUseridByMenuId (PowerEntity powerEntity);

    /**
     * 删除全部用户的指定可操作菜单表项
     * @param menuId 菜单表项唯一标识
     * @return 删除的菜单表项的个数
     */
//    @Delete("delete from t_power where menu_id=#{menuId}")
    int deleteAllUseridByMenuId (String menuId);
}
