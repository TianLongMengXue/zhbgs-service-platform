package com.e3e4e20.service.implement;

import com.e3e4e20.entity.mapper.DepartmentEntity;
import com.e3e4e20.exception.ErrorMessageException;
import com.e3e4e20.mapper.DepartmentMapper;
import com.e3e4e20.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final Logger log = LoggerFactory.getLogger("Class: DepartmentServiceImplement ");

    @Override
    @Transactional
    public String getDepartmentNameById(String uuid) {
        String departmentName = null;
        try {
            departmentName = departmentMapper.selectDepartmentNameById(uuid);
        } catch (Exception exception) {
            log.error("getDepartmentNameById: " + exception.getMessage());
        }
        if (null == departmentName) {
            log.error("getDepartmentNameById: 指定: " + uuid + " 部门不存在!");
            throw new ErrorMessageException("请确认该部门是否存在!");
        } else {
            log.info("getDepartmentNameById: 指定: " + uuid + ",部门名称: " + departmentName);
            return departmentName;
        }
    }

    @Override
    @Transactional
    public boolean departmentIsNotNull(String uuid) {
        DepartmentEntity departmentInfo = null;
        try {
            departmentInfo = departmentMapper.selectDepartmentById(uuid);
        } catch (Exception exception) {
            log.error("departmentIsNotNull: " + exception.getMessage());
        }
        if (null == departmentInfo) {
            log.error("departmentIsNotNull: 指定: " + uuid + " 部门不存在!");
            return false;
        } else {
            log.info("departmentIsNotNull: 指定: " + uuid + ",部门信息: " + departmentInfo);
            return true;
        }
    }
}
