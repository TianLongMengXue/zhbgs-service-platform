package com.e3e4e20.utils;

import java.util.UUID;

/*
Filename: Uuid
Created: 2023年04月12日 14时52分51秒 星期三
Author: 天龙梦雪
*/
public class Uuid {
    public String createUuid () {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
