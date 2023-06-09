package com.e3e4e20.config;

/*
Filename: CreateToken
Created: 2023年04月08日 00时11分05秒 星期六
Author: 天龙梦雪
*/

import com.e3e4e20.constant.ProjectDefaultConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenConfig {
    public String createdToken (String userid, boolean isStore) {
        Date now = new Date();
        Date expiration = null;
        if (isStore) {
            expiration = new Date(now.getTime() + 1000 * ProjectDefaultConfig.TOKEN_EXPIRE_MONTH);
        } else {
            expiration = new Date(now.getTime() + 1000 * ProjectDefaultConfig.TOKEN_EXPIRE_DAY);
        }
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(userid)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, ProjectDefaultConfig.TOKEN_SECRET)
                .compact();
    }

    public Claims getClaimsByToken (String token) {
        return Jwts.parser()
                .setSigningKey(ProjectDefaultConfig.TOKEN_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
