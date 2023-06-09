package com.e3e4e20.config;

import org.apache.commons.codec.digest.DigestUtils;

/*
Filename: CommonsCodecConfig
Created: 2023年05月11日 10时51分29秒 星期四
Author: 天龙梦雪
*/
public class CommonsCodecConfig {
    public String md5HexEncode (String data) {
        return DigestUtils.md5Hex(data);
    }

    public String sha256HexEncode (String data) {
        return DigestUtils.sha256Hex(data);
    }

    public String sha384HexEncode (String data) {
        return DigestUtils.sha384Hex(data);
    }

    public String sha512HexEncode (String data) {
        return DigestUtils.sha512Hex(data);
    }
}
