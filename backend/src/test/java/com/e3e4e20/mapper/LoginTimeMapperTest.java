package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.LoginTimeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/*
Filename: LoginTimeMapperTest
Created: 2023年06月26日 10时32分49秒 星期一
Author: 天龙梦雪
*/
@SpringBootTest
public class LoginTimeMapperTest {
    @Autowired
    private LoginTimeMapper loginTimeMapper;
    @Test
    void testSelectAllItemByUserid () {
        List<LoginTimeEntity> loginTimeEntityList = loginTimeMapper.selectAllItemByUserid(
                "1656857313497415680");
        System.out.println(loginTimeEntityList.size());
        System.out.println(loginTimeEntityList.toString());
    }
    @Test
    void testDeleteLoginTime () {
        int result = loginTimeMapper.deleteLoginTime("1656857313497415680");
        System.out.println("删除"+result+"条记录!");
    }
}
