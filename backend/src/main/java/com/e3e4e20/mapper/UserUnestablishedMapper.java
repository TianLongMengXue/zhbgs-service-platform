package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.UserUnestablishedEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: UserUnestablishedMapper
Created: 2023年07月17日 09时19分21秒 星期一
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface UserUnestablishedMapper {
    /**
     * 查询 t_unestablished 中所有编外人员的全部信息
     * @return 所有编外人员的全部信息
     */
    List<UserUnestablishedEntity> selectAllUserInfo();

    /**
     * 根据人员唯一标识从 t_unestablished 中查询该编外人员的全部信息
     * @param userid 人员唯一标识
     * @return 指定编外人员的全部信息
     */
    UserUnestablishedEntity selectUserInfoById (@Param("id") String userid);

    /**
     * 根据人员唯一标识修改 t_unestablished 中编外人员的信息
     * @param userUnestablishedEntity 编外人员的全部信息
     * @return 修改成功的记录的条数
     */
    Integer updateUserInfoById (UserUnestablishedEntity userUnestablishedEntity);

    /**
     * 向 t_unestablished 中添加编外人员信息
     * @param userUnestablishedEntity 编外人员的全部信息
     * @return 添加成功的记录条数
     */
    Integer insertUserInfo(UserUnestablishedEntity userUnestablishedEntity);

    /**
     * 根据人员唯一标识删除 t_unestablished 中编外人员的全部信息
     * @param userid 人员唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserInfoById(@Param("id") String userid);
}
