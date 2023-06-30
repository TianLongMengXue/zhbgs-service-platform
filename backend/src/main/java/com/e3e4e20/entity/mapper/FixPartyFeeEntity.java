package com.e3e4e20.entity.mapper;

/*
Filename: FixPartyFee
Created: 2023年04月25日 11时15分46秒 星期二
Author: 天龙梦雪
*/

/**
 * 固定党费缴纳用户
 * id : id 用户唯一标识(雪花算法)
 * fee : partyFee 固定党费
 */
public class FixPartyFeeEntity {
    private String id;
    private Double partyFee;

    public FixPartyFeeEntity() {
    }

    public FixPartyFeeEntity(String id, Double partyFee) {
        this.id = id;
        this.partyFee = partyFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPartyFee() {
        return partyFee;
    }

    public void setPartyFee(Double partyFee) {
        this.partyFee = partyFee;
    }

    @Override
    public String toString() {
        return "FixPartyFeeEntity{" +
                "userid='" + id + '\'' +
                ", partyFee=" + partyFee +
                '}';
    }
}
