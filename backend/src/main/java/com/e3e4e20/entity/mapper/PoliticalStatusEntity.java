package com.e3e4e20.entity.mapper;

/*
Filename: PoliticalStatusEntity
Created: 2023年04月25日 11时20分36秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户政治面貌信息
 * id : uuid 政治面貌名称唯一标识
 * name : politicalStatus 政治面貌名称
 */
public class PoliticalStatusEntity {
    private String uuid;
    private String politicalStatus;

    public PoliticalStatusEntity() {
    }

    public PoliticalStatusEntity(String uuid, String politicalStatus) {
        this.uuid = uuid;
        this.politicalStatus = politicalStatus;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
                "uuid='" + uuid + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                '}';
    }
}
