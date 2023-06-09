package com.e3e4e20.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/*
Filename: UuidTest
Created: 2023年04月13日 23时18分35秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class UuidTest {
    @Test
    public void testUUID () {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
    }
}
