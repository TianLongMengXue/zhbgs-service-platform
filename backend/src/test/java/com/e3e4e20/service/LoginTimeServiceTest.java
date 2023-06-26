package com.e3e4e20.service;

import com.e3e4e20.service.implement.LoginTimeServiceImplement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
Filename: LoginTimeServiceTest
Created: 2023年05月08日 16时38分07秒 星期一
Author: 天龙梦雪
*/
@SpringBootTest
public class LoginTimeServiceTest {
    @Autowired
    private final LoginTimeService loginTimeService = new LoginTimeServiceImplement();
    @Test
    void testGetCurrentTime() throws ParseException {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Date dateTime1 = simpleFormatter.parse("2023年5月7日 12时12分12秒");
        Date dateTime2 = simpleFormatter.parse("2023年5月8日 14时13分12秒");
        if (dateTime1.compareTo(dateTime2) > 0) {
            System.out.println(dateTime1 + ">" + dateTime2);
        } else if (dateTime1.compareTo(dateTime2) == 0) {
            System.out.println(dateTime1 + "=" + dateTime2);
        } else if (dateTime1.compareTo(dateTime2) < 0) {
            System.out.println(dateTime1 + "<" + dateTime2);
        }
    }
    @Test
    void testRecordLoginTime () throws ParseException {
        loginTimeService.recordLoginTime("1656857313497415680");
    }
    @Test
    void testTerminateLoginTime () {
        loginTimeService.terminateLoginTime("1656857313497415680");
    }
    @Test
    void testGetAllLoginTime () {

    }
}
