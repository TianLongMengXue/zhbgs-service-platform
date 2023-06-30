package com.e3e4e20.entity.mapper;

/*
Filename: PoliticalStatusEntity
Created: 2023年04月25日 11时20分36秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户政治面貌信息
 * id : id 政治面貌名称唯一标识(uuid)
 * name : politicalStatus 政治面貌名称
 */
public class PoliticalStatusEntity {
    private String id;
    private String politicalStatus;

    public PoliticalStatusEntity() {
    }

    public PoliticalStatusEntity(String id, String politicalStatus) {
        this.id = id;
        this.politicalStatus = politicalStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    @Override
    public String toString() {
        return "PoliticalStatusEntity{" +
                "uuid='" + id + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                '}';
    }
}
