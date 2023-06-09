package com.e3e4e20.mapper;

import com.e3e4e20.entity.mapper.LoginEntity;
import com.e3e4e20.utils.SnowFlake;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: LoginMapperTest
Created: 2023年04月13日 15时57分09秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class LoginMapperTest {
    /*
    * @Autowire报错导致的NullPointerException，虽然报错，项目运行没有问题。
    * 可是只有紧挨着@Autowire的一个起作用，下面的都没有注入成功。
    * 这时需要在每一个注入的Mapper中都加上@Autowire。
    * */
    @Autowired
    private LoginMapper loginMapper;
    @Test
    void testAddLoginUser () {
        /*LoginEntity loginEntity = new LoginEntity(
                String.valueOf(new SnowFlake().nextId()),
                "tianlongmengxue",
                "123456"
        );
        System.out.println(loginEntity.toString());
        System.out.println(loginMapper.addLoginUser(loginEntity));*/
    }
    @Test
    void testSelectUserid () {
        System.out.println(loginMapper.selectUserid("tianlongmengxue","123456"));
    }
    @Test
    void testSelectUserByUserid () {
        System.out.println(loginMapper.selectUserByUserid("1646434380698460160"));
    }
    @Test
    void testSelectAllLoginUser () {
        System.out.println(loginMapper.selectAllLoginUser().toString());
    }
    @Test
    void testUpdatePasswordByUserid () {
        System.out.println(loginMapper.updatePasswordByUserid("1646434380698460160","654321"));
    }
    @Test
    void testDeleteLoginUserByUserid () {
        System.out.println(loginMapper.deleteLoginUserByUserid(""));
    }
}
