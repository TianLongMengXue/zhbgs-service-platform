package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.UserApiEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: UserApiMapper
Created: 2023年04月28日 11时11分15秒 星期五
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface UserApiMapper {
    /**
     * 检索指定人员可以访问的全部 api 接口
     *
     * @param userid 人员唯一标识
     * @return 可访问的 api 接口的唯一标识
     */
    List<String> selectAllItemsByUserId(@Param("userid") String userid);

    /**
     * 检所指定人员的指定可访问 api 接口
     * @param userid 人员唯一标识
     * @param apiId api 接口唯一标识
     * @return 人员唯一标识和 api 接口唯一标识
     */
    UserApiEntity selectItemByUserAndApiId(@Param("userid") String userid, @Param("apiId") String apiId);

    /**
     * 为指定人员添加可访问的 api 接口
     *
     * @param userApiEntity 人员唯一标识和 api 接口唯一标识
     * @return 添加成功的记录条数
     */
    Integer insertUserApi(UserApiEntity userApiEntity);

    /**
     * 删除指定人员可访问的 api 接口
     *
     * @param userid 人员唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserApiByUserId(@Param("userid") String userid);

    /**
     * 删除具有指定 api 接口的人员
     *
     * @param apiId api 接口唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserApiByApiId(@Param("apiId") String apiId);

    /**
     * 删除指定人员可访问的指定 api 接口
     *
     * @param userApiEntity 人员唯一标识和 api 接口唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteUserApiByUserAndApiId(UserApiEntity userApiEntity);
}
