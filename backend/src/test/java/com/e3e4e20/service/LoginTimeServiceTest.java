package com.e3e4e20.service;

import org.junit.jupiter.api.Test;
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
}
