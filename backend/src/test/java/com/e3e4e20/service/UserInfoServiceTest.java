package com.e3e4e20.service;

import com.e3e4e20.entity.mapper.UserInfoEntity;
import com.e3e4e20.service.implement.UserInfoServiceImplement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: UserInfoServiceTest
Created: 2023年05月12日 11时29分27秒 星期五
Author: 天龙梦雪
*/
@SpringBootTest
public class UserInfoServiceTest {
    @Autowired
    private final UserInfoService userInfoService = new UserInfoServiceImplement();
    @Test
    void testUserIsNotNull () {
        System.out.println(userInfoService.userIsNotNull("1656857313497415680"));
    }
    @Test
    void testGetUserInfoByUserid () {
        UserInfoEntity userInfoEntity = userInfoService.getUserInfoByUserid("1656857313497415680");
        System.out.println(userInfoEntity);
    }
}
