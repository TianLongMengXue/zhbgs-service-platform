package com.e3e4e20.service;

/*
Filename: DepartmentService
Created: 2023年05月10日 16时38分44秒 星期三
Author: 天龙梦雪
*/
public interface DepartmentService {
    /**
     * 根据部门唯一标识获取部门名称
     * @param uuid 部门唯一标识
     * @return 部门名称 or null
     */
    String getDepartmentNameById (String uuid);
}
