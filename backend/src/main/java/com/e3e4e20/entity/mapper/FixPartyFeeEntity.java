package com.e3e4e20.entity.mapper;

/*
Filename: FixPartyFee
Created: 2023年04月25日 11时15分46秒 星期二
Author: 天龙梦雪
*/

/**
 * 固定党费缴纳用户
 * id : userid 用户唯一标识
 * fee : partyFee 固定党费
 */
public class FixPartyFeeEntity {
    private String userid;
    private Double partyFee;

    public FixPartyFeeEntity() {
    }

    public FixPartyFeeEntity(String userid, Double partyFee) {
        this.userid = userid;
        this.partyFee = partyFee;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
                "userid='" + userid + '\'' +
                ", partyFee=" + partyFee +
                '}';
    }
}
