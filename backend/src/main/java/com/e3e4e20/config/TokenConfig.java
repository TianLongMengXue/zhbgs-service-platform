package com.e3e4e20.config;

/*
Filename: CreateToken
Created: 2023年04月08日 00时11分05秒 星期六
Author: 天龙梦雪
*/

import com.e3e4e20.constant.ProjectDefaultConfig;
import com.e3e4e20.exception.FailureMessageException;
import com.e3e4e20.exception.UnverifiedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class TokenConfig {
    private final Logger log = LoggerFactory.getLogger("Class: TokenConfig");
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

    public Claims getClaimsByToken (String token) throws UnverifiedException {
        try {
            return Jwts.parser()
                    .setSigningKey(ProjectDefaultConfig.TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception exception) {
            log.error("getClaimsByToken: "+exception.getMessage());
            log.error("getClaimsByToken: token is expiration! token 已过期 !");
            throw new UnverifiedException();
        }
    }
}
