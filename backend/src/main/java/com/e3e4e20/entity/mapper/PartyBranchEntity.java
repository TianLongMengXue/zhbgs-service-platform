package com.e3e4e20.entity.mapper;

/*
Filename: PartyBranchEntity
Created: 2023年04月25日 11时18分28秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户所属党支部名称
 * id : uuid 党支部名称唯一标识
 * name : partyName 党支部名称
 */
public class PartyBranchEntity {
    private String uuid;
    private String partyName;

    public PartyBranchEntity() {
    }

    public PartyBranchEntity(String uuid, String partyName) {
        this.uuid = uuid;
        this.partyName = partyName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    @Override
    public String toString() {
        return "PartyBranchEntity{" +
                "uuid='" + uuid + '\'' +
                ", partyName='" + partyName + '\'' +
                '}';
    }
}
