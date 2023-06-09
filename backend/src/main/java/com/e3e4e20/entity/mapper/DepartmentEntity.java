package com.e3e4e20.entity.mapper;

/*
Filename: DepartmentEntity
Created: 2023年04月25日 11时08分18秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户所属部门的部门名称表
 * id : uuid 部门名称唯一标识
 * name : departmentName 部门名称
 */
public class DepartmentEntity {
    private String uuid;
    private String departmentName;

    public DepartmentEntity() {
    }

    public DepartmentEntity(String uuid, String departmentName) {
        this.uuid = uuid;
        this.departmentName = departmentName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
                "uuid='" + uuid + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
