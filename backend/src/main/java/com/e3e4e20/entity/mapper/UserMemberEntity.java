package com.e3e4e20.entity.mapper;

/*
Filename: UserMemberEntity
Created: 2023年07月17日 08时50分27秒 星期一
Author: 天龙梦雪
*/

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * 在职人员信息实体类
 * id : id : 人员唯一标识
 * name : name : 人员姓名
 * department : department : 人员所属部门
 * phone : phone : 人员移动电话
 * landline : landline : 人员固定电话(无 - none)
 * political_status : politicalStatus : 人员政治面貌
 * party_branch : partyBranch : 人员所属党支部(无 - none)
 * time : time : 人员信息添加时间
 */
public class UserMemberEntity {
    private String id;
    private String name;
    private String department;
    private String phone;
    private String landline;
    private String politicalStatus;
    private String partyBranch;
    private LocalDateTime time;

    public UserMemberEntity() {
    }

    public UserMemberEntity(String id, String name, String department, String phone, String landline, String politicalStatus, String partyBranch, LocalDateTime time) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.phone = phone;
        this.landline = landline;
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

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
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
        return "UserMemberEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", landline='" + landline + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", partyBranch='" + partyBranch + '\'' +
                ", time=" + time +
                '}';
    }
}
