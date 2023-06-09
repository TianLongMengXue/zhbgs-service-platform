package com.e3e4e20.config;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: LoggerTest
Created: 2023年04月20日 16时23分46秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class LoggerTest {
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    void testLogging() {
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }
}
