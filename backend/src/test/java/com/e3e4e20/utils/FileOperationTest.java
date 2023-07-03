package com.e3e4e20.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
Filename: FileOperationTest
Created: 2023年07月03日 11时26分04秒 星期一
Author: 天龙梦雪
*/
@SpringBootTest
public class FileOperationTest {
    @Test
    void testGetFileExtension() {
        String filePath = "C:\\Users\\JING\\Pictures\\Screenshots\\屏幕截图 2023-06-08 113343.png";
        System.out.println(new FileOperation().getFileExtension(filePath));
    }
}
