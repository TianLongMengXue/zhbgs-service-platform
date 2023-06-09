package com.e3e4e20.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: Pinyin4JConfigTest
Created: 2023年04月13日 23时19分35秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class Pinyin4JConfigTest {
    @Test
    public void testHanYuPinYin () {
        System.out.println(new Pinyin4JConfig().getLowerPingYin("天龙梦雪"));
        System.out.println(new Pinyin4JConfig().getUpperAlphabet("天"));
    }
}
