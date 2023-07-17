package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.UserRetiringEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: UserRetiringMapper
Created: 2023年07月17日 09时19分21秒 星期一
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface UserRetiringMapper {
    /**
     * 查询 t_retiring 中所有退休人员的全部信息
     * @return 所有退休人员的全部信息
     */
    List<UserRetiringEntity> selectAllUserInfo();

    /**
     * 根据人员唯一标识从 t_retiring 中查询该退休人员的全部信息
     * @param userid 人员唯一标识
     * @return 指定退休人员的全部信息
     */
    UserRetiringEntity selectUserInfoById (@Param("id") String userid);

    /**
     * 根据人员唯一标识修改 t_retiring 中退休人员的信息
     * @param userRetiringEntity 退休人员的全部信息
     * @return 修改成功的记录的条数
     */
    Integer updateUserInfoById (UserRetiringEntity userRetiringEntity);

    /**
     * 向 t_retiring 中添加退休人员信息
     * @param userRetiringEntity 退休人员的全部信息
     * @return 添加成功的记录条数
     */
    Integer insertUserInfo(UserRetiringEntity userRetiringEntity);

    /**
     * 根据人员唯一标识删除 t_retiring 中退休人员的全部信息
     * @param userid 人员唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserInfoById(@Param("id") String userid);
}
