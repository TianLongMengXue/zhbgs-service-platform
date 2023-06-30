package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.DepartmentEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Filename: DepartmentMapper
Created: 2023年04月25日 17时10分23秒 星期二
Author: 天龙梦雪
*/
@Mapper
@Repository
public interface DepartmentMapper {
    /**
     * 查询全部的部门名称
     * @return 全部的部门名称和部门唯一标识
     */
    List<DepartmentEntity> selectAllDepartment ();

    /**
     * 根据部门唯一标识获取部门名称
     * @param uuid 部门唯一标识
     * @return 部门名称
     */
    String selectDepartmentNameById(@Param("id") String uuid);

    /**
     * 根据指定部门唯一标识检索部门信息
     * @param uuid 部门唯一标识
     * @return 部门全部信息
     */
    DepartmentEntity selectDepartmentById (@Param("id") String uuid);

    /**
     * 添加一条部门名称记录
     * @param departmentEntity 部门名称和部门的唯一标识
     * @return 添加的成功的记录条数
     */
    int insertDepartment (DepartmentEntity departmentEntity);

    /**
     * 修改部门的名称
     * @param departmentEntity 部门的唯一标识和部门的新名称
     * @return 修改成功的记录条数
     */
    int updateDepartment (DepartmentEntity departmentEntity);

    /**
     * 删除一条部门名称记录
     * @param departmentId 部门的唯一标识
     * @return 删除成功的记录条数
     */
    int deleteDepartment (@Param("id") String departmentId);
}
