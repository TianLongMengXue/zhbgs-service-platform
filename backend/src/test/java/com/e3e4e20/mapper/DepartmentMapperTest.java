package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.DepartmentEntity;
import com.e3e4e20.utils.Uuid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/*
Filename: DepartmentMapperTest
Created: 2023年05月11日 16时37分05秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class DepartmentMapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Test
    void testInsertDepartment () {
        String[] departmentName = {"综合办公室", "财务资产管理与服务管理中心", "科研管理中心", "科技政策研究中心（《安徽科技》编辑部）", "区域创新研究中心", "产业技术研究中心", "科技统计中心", "科技信息网络中心", "科技宣传与信息传播中心", "科技档案管理中心", "科技文献服务中心", "科技查新中心"};
        int result = 0, count = 0;
        for (String name : departmentName) {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setUuid(new Uuid().createUuid());
            departmentEntity.setDepartmentName(name);
            result = departmentMapper.insertDepartment(departmentEntity);
            count += result;
        }
        System.out.println("count="+count);
    }
    @Test
    void testSelectAllItem () {
        List<DepartmentEntity> departmentEntityList = departmentMapper.selectAllDepartment();
        System.out.println(departmentEntityList.toString());
    }
}
