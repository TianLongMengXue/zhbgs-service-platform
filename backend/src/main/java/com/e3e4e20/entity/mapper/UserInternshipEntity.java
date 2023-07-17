package com.e3e4e20.entity.mapper;

/*
Filename: UserInternshipEntity
Created: 2023年07月17日 09时13分12秒 星期一
Author: 天龙梦雪
*/

import java.time.LocalDateTime;

/**
 * 见/实习生信息实体类
 * id : id : 人员唯一标识
 * name : name : 人员姓名
 * department : department : 人员所属部门
 * phone : phone : 人员移动电话
 * time : time : 人员信息添加时间
 */
public class UserInternshipEntity {
    private String id;
    private String name;
    private String department;
    private String phone;
    private LocalDateTime time;

    public UserInternshipEntity() {
    }

    public UserInternshipEntity(String id, String name, String department, String phone, LocalDateTime time) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.phone = phone;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserInternshipEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", time=" + time +
                '}';
    }
}
