package com.e3e4e20.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: SnowFlakeTest
Created: 2023年04月13日 23时16分28秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class SnowFlakeTest {
    @Test
    public void testSnowFlake () {
        // 雪花算法的生成的userid
        System.out.println(new SnowFlake().nextId());
    }
}
