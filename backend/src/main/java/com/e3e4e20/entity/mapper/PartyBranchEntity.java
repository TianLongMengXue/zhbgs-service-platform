package com.e3e4e20.entity.mapper;

/*
Filename: PartyBranchEntity
Created: 2023年04月25日 11时18分28秒 星期二
Author: 天龙梦雪
*/

/**
 * 用户所属党支部名称
 * id : id 党支部名称唯一标识(uuid)
 * name : partyName 党支部名称
 */
public class PartyBranchEntity {
    private String id;
    private String partyName;

    public PartyBranchEntity() {
    }

    public PartyBranchEntity(String id, String partyName) {
        this.id = id;
        this.partyName = partyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
                "uuid='" + id + '\'' +
                ", partyName='" + partyName + '\'' +
                '}';
    }
}
