package com.e3e4e20.service;

/*
Filename: PoliticalStatusService
Created: 2023年05月10日 17时09分09秒 星期三
Author: 天龙梦雪
*/
public interface PoliticalStatusService {
    /**
     * 根据政治面貌唯一标识获取政治面貌唯一标识
     * @param uuid 政治面貌唯一标识
     * @return  政治面貌唯一标识 or null
     */
    String getPoliticalStatusName(String uuid);
}
