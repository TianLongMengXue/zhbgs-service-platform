package com.e3e4e20.service;

/*
Filename: PartyBranchService
Created: 2023年05月10日 17时24分45秒 星期三
Author: 天龙梦雪
*/
public interface PartyBranchService {
    /**
     * 根据党支部唯一标识获取党支部名称
     * @param uuid 党支部唯一标识
     * @return 党支部名称
     */
    String getPartyBranchName (String uuid);

    /**
     * 根据党支部唯一标识判断该党支部是否存在
     * @param uuid 党支部唯一标识
     * @return true or false or Exception
     */
    boolean partyBranchIsNotNull (String uuid);
}
