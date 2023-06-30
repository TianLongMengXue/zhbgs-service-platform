package com.e3e4e20.entity.mapper;

/*
Filename: UserInfoEntity
Created: 2023年04月25日 11时24分22秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户信息
 * id : id 用户唯一标识(雪花算法)
 * name : name 姓名
 * department : departmentId 所属部门
 * post : postId 岗位
 * political_status : politicalStatusId 政治面貌
 * party_branch : partyBranchId 所属党支部
 */
public class UserInfoEntity {
    private String id;
    private String name;
    private String department;
    private String post;
    private String politicalStatus;
    private String partyBranch;

    public UserInfoEntity() {
    }

    public UserInfoEntity(String id, String name, String department, String post, String politicalStatus, String partyBranch) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.post = post;
        this.politicalStatus = politicalStatus;
        this.partyBranch = partyBranch;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "userid='" + id + '\'' +
                ", name='" + name + '\'' +
                ", departmentId='" + department + '\'' +
                ", postId='" + post + '\'' +
                ", politicalStatusId='" + politicalStatus + '\'' +
                ", partyBranchId='" + partyBranch + '\'' +
                '}';
    }
}
