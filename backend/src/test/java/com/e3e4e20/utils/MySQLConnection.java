package com.e3e4e20.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

/*
Filename: MySQLConnection
Created: 2023年04月13日 23时08分26秒 星期四
Author: 天龙梦雪
*/
@SpringBootTest
public class MySQLConnection {
    @Autowired
    private DataSource dataSource;
    @Test
    public void testDataSource() throws SQLException {
        // 测试数据源（数据库连接是否成功）
        System.out.println(dataSource.getConnection());
    }
}
