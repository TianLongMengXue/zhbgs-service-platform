package com.e3e4e20.entity.mapper;

/*
Filename: DepartmentEntity
Created: 2023年04月25日 11时08分18秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户所属部门的部门名称表
 * id : id 部门名称唯一标识(uuid)
 * name : departmentName 部门名称
 */
public class DepartmentEntity {
    private String id;
    private String departmentName;

    public DepartmentEntity() {
    }

    public DepartmentEntity(String id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "uuid='" + id + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
