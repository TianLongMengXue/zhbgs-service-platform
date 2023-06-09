package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.ApiEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: ApiMapper
Created: 2023年04月28日 16时23分59秒 星期五
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface ApiMapper {
    /**
     * 检索所有非系统管理员 api 接口
     * @return 所有非系统管理员权限
     */
    List<ApiEntity> selectAllItemWithoutAdmin ();

    /**
     * 检索所有的 api 接口
     * @return 所有的 api 接口
     */
    List<ApiEntity> selectAllItemWithAdmin ();

    /**
     * 添加新的 api 接口
     * @param apiEntity api接口信息
     * @return 添加成功的记录条数
     */
    Integer insertApi (ApiEntity apiEntity);

    /**
     * 修改 api 接口信息
     * @param apiEntity api接口信息
     * @return 修改成功的记录条数
     */
    Integer updateApi (ApiEntity apiEntity);

    /**
     * 删除 api 接口
     * @param uuid api 接口唯一标识
     * @return 删除成功的记录条数
     */
    Integer deleteApi (@Param("id") String uuid);
}
