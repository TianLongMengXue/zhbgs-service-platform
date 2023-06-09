package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.DepartmentEntity;
import com.e3e4e20.mapper.DepartmentMapper;
import com.e3e4e20.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
Filename: DepartmentServiceImplement
Created: 2023年05月10日 16时40分56秒 星期三
Author: 天龙梦雪
*/
@Service(value = "departmentService")
public class DepartmentServiceImplement implements DepartmentService {
    @Resource(type = DepartmentMapper.class)
    private DepartmentMapper departmentMapper;
    private final Logger logger = LoggerFactory.getLogger("Class:DepartmentServiceImplement");
    @Override
    public String getDepartmentNameById(String uuid) {
        DepartmentEntity departmentEntity = null;
        try {
            departmentEntity = departmentMapper.selectDepartmentById(uuid);
        } catch (Exception ignore) {
        }
        if (null == departmentEntity) {
            logger.error("指定:"+uuid+"部门不存在!");
            return null;
        } else {
            logger.debug("指定:"+uuid+"部门信息为"+ departmentEntity.toString());
            return departmentEntity.getDepartmentName();
        }
    }
}
