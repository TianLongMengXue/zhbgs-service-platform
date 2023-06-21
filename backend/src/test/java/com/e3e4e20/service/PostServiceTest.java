package com.e3e4e20.service;

import com.e3e4e20.service.implement.PostServiceImplement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: PostServiceTest
Created: 2023年06月19日 17时00分34秒 星期一
Author: 天龙梦雪
*/
@SpringBootTest
public class PostServiceTest {
    @Autowired
    private final PostService postService = new PostServiceImplement();
    @Test
    void testGetPostNameById () {
        System.out.println(postService.getPostNameById("0d81f1998367439f8fd11811cbc1eb11"));
    }
}
