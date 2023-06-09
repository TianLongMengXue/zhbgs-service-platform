package com.e3e4e20.excpetion;

import com.e3e4e20.exception.FailureMessageException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: MessageExceptionTest
Created: 2023年04月20日 10时47分44秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class FailureMessageExceptionTest {
    @Test
    void testMessageException () throws Exception {
        throw new FailureMessageException("这是一个exception测试！");
    }
}
