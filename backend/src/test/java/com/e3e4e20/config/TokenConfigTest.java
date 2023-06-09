package com.e3e4e20.config;

import com.e3e4e20.utils.SnowFlake;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/*
Filename: TokenConfigTest
Created: 2023年04月13日 23时17分31秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class TokenConfigTest {
    @Test
    public void testCreatedToken () {
        // 生成token和解析token
        String token = new TokenConfig().createdToken(String.valueOf(new SnowFlake().nextId()),false);
        System.out.println(token);
        Claims claims = new TokenConfig().getClaimsByToken(token);
        System.out.println(claims);
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
        System.out.println(new Date());
        System.out.println(claims.getExpiration().before(new Date()));
        System.out.println(claims.getExpiration().after(new Date()));
    }
}
