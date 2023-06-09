package com.e3e4e20.utils;

import com.e3e4e20.constant.ProjectDefaultConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: CommonsCodecTest
Created: 2023年05月11日 10时33分36秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class CommonsCodecTest {
    @Test
    void md5HexTest() {
        String encode = DigestUtils.md5Hex(ProjectDefaultConfig.PROJECT_DEFAULT_PASSWORD);
        System.out.println("123456 md5Hex encode : " + encode);
    }

    @Test
    void sha256Test() {
        String encode = DigestUtils.sha256Hex(ProjectDefaultConfig.PROJECT_DEFAULT_PASSWORD);
        System.out.println("123456 sha256 encode : " + encode);
    }

    @Test
    void sha384Test() {
        String encode = DigestUtils.sha384Hex(ProjectDefaultConfig.PROJECT_DEFAULT_PASSWORD);
        System.out.println("123456 sha384 encode : " + encode);
    }

    @Test
    void sha512Test() {
        String encode = DigestUtils.sha512Hex(ProjectDefaultConfig.PROJECT_DEFAULT_PASSWORD);
        System.out.println("123456 sha512 encode : " + encode);
    }
}
