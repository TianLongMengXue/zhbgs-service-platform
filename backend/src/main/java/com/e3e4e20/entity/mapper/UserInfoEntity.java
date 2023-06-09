package com.e3e4e20.entity.mapper;

/*
Filename: UserInfoEntity
Created: 2023年04月25日 11时24分22秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户信息
 * id : userid 用户唯一标识
 * name : name 姓名
 * department : departmentId 所属部门
 * post : postId 岗位
 * political_status : politicalStatusId 政治面貌
 * party_branch : partyBranchId 所属党支部
 */
public class UserInfoEntity {
    private String userid;
    private String name;
    private String departmentId;
    private String postId;
    private String politicalStatusId;
    private String partyBranchId;

    public UserInfoEntity() {
    }

    public UserInfoEntity(String userid, String name, String departmentId, String postId, String politicalStatusId, String partyBranchId) {
        this.userid = userid;
        this.name = name;
        this.departmentId = departmentId;
        this.postId = postId;
        this.politicalStatusId = politicalStatusId;
        this.partyBranchId = partyBranchId;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPoliticalStatusId() {
        return politicalStatusId;
    }

    public void setPoliticalStatusId(String politicalStatusId) {
        this.politicalStatusId = politicalStatusId;
    }

    public String getPartyBranchId() {
        return partyBranchId;
    }

    public void setPartyBranchId(String partyBranchId) {
        this.partyBranchId = partyBranchId;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", postId='" + postId + '\'' +
                ", politicalStatusId='" + politicalStatusId + '\'' +
                ", partyBranchId='" + partyBranchId + '\'' +
                '}';
    }
}
