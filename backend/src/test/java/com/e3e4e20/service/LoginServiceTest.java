package com.e3e4e20.service;

import com.e3e4e20.service.implement.LoginServiceImplement;
import com.e3e4e20.service.implement.LoginTimeServiceImplement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/*
Filename: LoginServiceTest
Created: 2023年06月26日 15时54分34秒 星期一
Author: 天龙梦雪
*/
@SpringBootTest
public class LoginServiceTest {
    @Autowired
    private final LoginService loginService = new LoginServiceImplement();
    @Autowired
    private final LoginTimeService loginTimeService = new LoginTimeServiceImplement();
    @Test
    @Transactional
    void testAnnulLoginPrivilege () {
        loginTimeService.terminateLoginTime("1656857313497415680");
        loginService.deleteLoginUser("1656857313497415680");
    }
}
