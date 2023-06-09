package com.e3e4e20.service;

import com.e3e4e20.service.implement.MenuServiceImplement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: MenuServiceTest
Created: 2023年04月14日 21时28分34秒 星期五
Author: 天龙梦雪
*/
@SpringBootTest
public class MenuServiceTest {
    @Autowired
    private final MenuService menuService = new MenuServiceImplement();
    @Test
    void testGetAllMenuItem () {
        System.out.println(menuService.getAllMenuItem());
    }
}
