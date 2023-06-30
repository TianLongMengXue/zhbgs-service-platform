package com.e3e4e20.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/*
Filename: StringBuilderTest
Created: 2023年06月30日 10时20分32秒 星期五
Author: 天龙梦雪
*/
@SpringBootTest
public class StringBuilderTest {
    @Test
    void testStringBuilder() {
        // String str = "https://blog.csdn.net/JeremyTsai/article/details/109267783/";
        // String str = "https:\\\\blog.csdn.net\\JeremyTsai\\article\\details\\109267783\\";
        // String str = "c:/Archive/Picture/avatar.ico";
        // String str = "c:/Archive/Picture/avatar.index.ico";
        String str = "c:/Archive/Picture/avatar.index.original.ico";
        String separator = "/|\\\\";
        String[] strings = str.split(separator);
        System.out.println("strings: " + Arrays.toString(strings));
        System.out.println("last item in strings: " + strings[strings.length - 1]);
        String str2 = str.substring(0, (str.length() - strings[strings.length - 1].length()));
        System.out.println("remove last item of strings in str: "+str2);
        StringBuilder path = null;
        String path2 = "";
        for (int i = 0; i < strings.length - 1; i++) {
            if (null == strings[i] || strings[i].equals("")) {
                continue;
            }
            path = new StringBuilder().append(strings[i]).append("/");
            path2 = path2.concat(strings[i]);
            path2 = path2.concat("/");
        }
        System.out.println("path: " + path);
        System.out.println("path2: " + path2);

        String lastItem = "";
        if (strings[strings.length - 1].contains(".")) {
            String[] strings2 = strings[strings.length - 1].split("\\.");
            System.out.println("strings2: "+ Arrays.toString(strings2));
            for (int i = 0; i < strings2.length - 1; i++) {
                lastItem = lastItem.concat(strings2[i]);
                lastItem = lastItem.concat(".");
            }
            lastItem = lastItem.substring(0, lastItem.length() - 1);
        } else {
            lastItem = strings[strings.length - 1];
        }
        System.out.println("lastItem: "+lastItem);
    }
}
