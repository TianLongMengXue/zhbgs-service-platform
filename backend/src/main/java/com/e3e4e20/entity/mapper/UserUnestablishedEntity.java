package com.e3e4e20.entity.mapper;

/*
Filename: UserUnestablishedEntity
Created: 2023年07月17日 09时05分38秒 星期一
Author: 天龙梦雪
*/

import java.time.LocalDateTime;

/**
 * 编外人员信息实体类
 * id : id : 人员唯一标识
 * name : name : 人员姓名
 * department : department : 人员所属部门
 * phone : phone : 人员移动电话
 * political_status : politicalStatus : 人员政治面貌
 * party_branch : partyBranch : 人员所属党支部(无 - none)
 * time : time : 人员信息添加时间
 */
public class UserUnestablishedEntity {
    private String id;
    private String name;
    private String department;
    private String phone;
    private String politicalStatus;
    private String partyBranch;
    private LocalDateTime time;

    public UserUnestablishedEntity() {
    }

    public UserUnestablishedEntity(String id, String name, String department, String phone, String politicalStatus, String partyBranch, LocalDateTime time) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.phone = phone;
        this.politicalStatus = politicalStatus;
        this.partyBranch = partyBranch;
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

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getPartyBranch() {
        return partyBranch;
    }

    public void setPartyBranch(String partyBranch) {
        this.partyBranch = partyBranch;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "UserUnestablishedEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", partyBranch='" + partyBranch + '\'' +
                ", time=" + time +
                '}';
    }
}
