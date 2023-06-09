package com.e3e4e20.mapper;

/*
Filename: LoginTimeMapper
Created: 2023年05月04日 09时09分38秒 星期四
Author: 天龙梦雪
*/

import com.e3e4e20.entity.mapper.LoginTimeEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface LoginTimeMapper {
    /**
     * 根据人员唯一标识检索全部的登录时间
     * @return 当前登录人员的全部登录时间
     */
    List<LoginTimeEntity> selectAllItemByUserid (@Param("userid") String userid);

    /**
     * 根据行唯一标识和人员唯一标识修改登录时间
     * @param loginTimeEntity 行唯一标识和人员唯一标识和登录时间
     * @return 修改成功的记录条数
     */
    Integer updateLoginTime (LoginTimeEntity loginTimeEntity);

    /**
     * 添加人员的登录时间
     * @param userid 当前人员的唯一标识
     * @param loginTime 当前人员的登录时间
     * @return 添加成功的记录条数
     */
    Integer insertLoginTime (@Param("userid") String userid, @Param("time") LocalDateTime loginTime);

    /**
     * 删除制定人员全部的登录时间
     * @param userid 人员唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteLoginTime (@Param("userid") String userid);
}
