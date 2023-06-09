package com.e3e4e20.service;


/*
Filename: PostService
Created: 2023年05月05日 09时44分16秒 星期五
Author: 天龙梦雪
*/
public interface PostService {
    /**
     * 根据岗位唯一标识获取岗位名称
     * @param uuid 岗位唯一标识
     * @return 岗位名称
     */
    String getPostNameById (String uuid);
}
