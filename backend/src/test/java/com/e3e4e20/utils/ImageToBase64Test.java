package com.e3e4e20.utils;

import com.e3e4e20.constant.ProjectDefaultConfig;
import org.apache.tika.Tika;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.activation.MimetypesFileTypeMap;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

/*
Filename: Base64EncoderTest
Created: 2023年05月19日 15时21分42秒 星期五
Author: 天龙梦雪
*/
@SpringBootTest
public class ImageToBase64Test {
    private String[] fileList = null;

    @Test
    void testGrantProperties() throws Exception {
        /*System.out.println(new ImageToBase64().imageToBase64Encoder("idea.ico"));
        System.out.println(new ImageToBase64().imageToBase64Encoder("idea.png"));*/
//        String imagePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH+"test.ico";
//        System.out.println(new ImageToBase64(imagePath).getImageSrcUrl());
//        System.out.println(new ImageToBase64(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH,"test.ico").getImageSrcUrl());
        String imagePath2 = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "test.ts";
        System.out.println(new ImageToBase64(imagePath2).getImageType());
        System.out.println("=========================================================================================");
        System.out.println(new ImageToBase64(imagePath2).getImageBase64Encoder());
//        System.out.println(new ImageToBase64(imagePath2).getImageSrcUrl());
    }

    @Test
    void getDirectoryFile() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
                System.out.println(fileOrDirectory.toString() + ": 文件夹!");
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    File file = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i]);
                    if (file.isFile()) {
                        System.out.println(file.toString() + ": 文件!");
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void stringSplitGetFileType() throws Exception {
        try {
            File fileOrDirectory = new File("C:/Archive/Pictures/");
            if (fileOrDirectory.isDirectory()) {
                String[] fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    File file = new File("C:/Archive/Pictures/" + fileList[i]);
                    if (file.isFile()) {
                        String[] strings = fileList[i].split("\\.");
                        System.out.println(file.getName() + ": " + strings[strings.length - 1]);
                        ;
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void getDirectoryFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
                System.out.println(fileOrDirectory.toString() + ": 文件夹!");
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
                        System.out.println(file.toString() + ": 文件!");
                        System.out.println("=========================================================================");
                        {
                            try {
                                Path path = new File(filePath).toPath();
                                System.out.println(filePath + ",probeContentType File type:" + Files.probeContentType(path));
                            } catch (Exception e) {
                                System.out.println(filePath + ",probeContentType File Error type:" + e);
                            }
                        }
                        System.out.println("=========================================================================");
                        {
                            try {
                                URLConnection connection = file.toURI().toURL().openConnection();
                                System.out.println(filePath + ",URLConnection getContentType File type:" + connection.getContentType());
                            } catch (Exception e) {
                                System.out.println(filePath + ",URLConnection getContentType File Error type:" + e);
                            }
                        }
                        System.out.println("=========================================================================");
                        {
                            try {
                                System.out.println(filePath + ",guessContentTypeFromName File type:" + URLConnection.guessContentTypeFromName(file.getName()));
                            } catch (Exception e) {
                                System.out.println(filePath + ",guessContentTypeFromName File Error type:" + e);
                            }
                        }
                        System.out.println("=========================================================================");
                        {

                            try {
                                FileInputStream fileInputStream = new FileInputStream(filePath);
                                System.out.println(filePath + ",guessContentTypeFromStream File type:" + URLConnection.guessContentTypeFromStream(fileInputStream));
                            } catch (Exception e) {
                                System.out.println(filePath + ",guessContentTypeFromStream File Error type:" + e);
                            }
                        }
                        System.out.println("=========================================================================");
                        {
                            try {
                                FileNameMap fileNameMap = URLConnection.getFileNameMap();
                                System.out.println(filePath + ",getContentTypeFor File type:" + fileNameMap.getContentTypeFor(file.getName()));
                            } catch (Exception e) {
                                System.out.println(filePath + ",getContentTypeFor File Error type:" + e);
                            }
                        }
                        System.out.println("=========================================================================");
                        {
                            try {
                                MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
                                System.out.println(filePath + ",MimetypesFileTypeMap File type:" + mimetypesFileTypeMap.getContentType(file.getName()));
                            } catch (Exception e) {
                                System.out.println(filePath + ",MimetypesFileTypeMap File Error type:" + e);
                            }
                        }
                        System.out.println("=========================================================================");
                        {
                            /*try {
                                MagicMatch magicMatch = Magic.getMagicMatch(file, false);
                                System.out.println(filePath + ",MagicMatch File type:" + magicMatch.getMimeType());
                            } catch (Exception e) {
                                System.out.println(filePath + ",MagicMatch File Error type:" + e);
                            }*/
                        }
                        System.out.println("=========================================================================");
                        {
                            try {
                                Tika tika = new Tika();
                                System.out.println(filePath + ",Tika File type:" + tika.detect(file));
                            } catch (Exception e) {
                                System.out.println(filePath + ",Tika File Error type:" + e);
                            }
                        }
                        System.out.println("=========================================================================");
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void MimetypesFileTypeMapGetFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
//                System.out.println(fileOrDirectory.toString()+": 文件夹!");
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
//                        System.out.println(file.toString()+": 文件!");
                        {
                            try {
                                MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
                                System.out.println(file.getName() + ": " + mimetypesFileTypeMap.getContentType(file.getName()));
                            } catch (Exception e) {
                                System.out.println(file.getName() + ": " + e.toString());
                            }
                        }
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            } else {
                System.out.println(fileOrDirectory.toString() + ": 文件类型未知!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void probeContentTypeGetDirectoryFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
//                System.out.println(fileOrDirectory.toString()+": 文件夹!");
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
//                        System.out.println(file.toString()+": 文件!");
                        {
                            try {
                                Path path = new File(filePath).toPath();
                                System.out.println(file.getName() + ",type: " + Files.probeContentType(path));
                            } catch (Exception e) {
                                System.out.println(file.getName() + ",Error type: " + e);
                            }
                        }
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            } else {
                System.out.println(fileOrDirectory.toString() + ": 文件类型未知!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void guessContentTypeFromNameGetDirectoryFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
//                System.out.println(fileOrDirectory.toString()+": 文件夹!");
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
//                        System.out.println(file.toString()+": 文件!");
                        {
                            try {
                                System.out.println(file.getName() + ": " + URLConnection.guessContentTypeFromName(file.getName()));
                            } catch (Exception e) {
                                System.out.println(file.getName() + ": " + e.toString());
                            }
                        }
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            } else {
                System.out.println(fileOrDirectory.toString() + ": 文件类型未知!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void getContentTypeForGetDirectoryFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
//                System.out.println(fileOrDirectory.toString()+": 文件夹!");
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
//                        System.out.println(file.toString()+": 文件!");
                        {
                            try {
                                FileNameMap fileNameMap = URLConnection.getFileNameMap();
                                System.out.println(file.getName() + ": " + fileNameMap.getContentTypeFor(file.getName()));
                            } catch (Exception e) {
                                System.out.println(file.getName() + ": " + e.toString());
                            }
                        }
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            } else {
                System.out.println(fileOrDirectory.toString() + ": 文件类型未知!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void getSystemPropertyUserHome() {
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("META-INF"));
        System.out.println(System.getProperty("/META-INF/mimetypes.default"));
    }

    @Test
    void getFileTypeUtilGetDirectoryFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
//                System.out.println(fileOrDirectory.toString()+": 文件夹!");
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
//                        System.out.println(file.toString()+": 文件!");
                        {
                            try {
                                String name = GetFileTypeUtils.getType(filePath).name();
                                System.out.println(file.getName() + ",文件格式是:" + name);
                            } catch (Exception e) {
                                System.out.println(file.getName() + ": " + e.toString());
                            }
                        }
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            } else {
                System.out.println(fileOrDirectory.toString() + ": 文件类型未知!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void getContentTypeGetDirectoryFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
                        {
                            try {
                                URLConnection connection = file.toURL().openConnection();
                                System.out.println(file.getName() + ": " + connection.getContentType());
                            } catch (Exception e) {
                                System.out.println(file.getName() + ",File Error type: " + e.toString());
                            }
                        }
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            } else {
                System.out.println(fileOrDirectory.toString() + ": 文件类型未知!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void guessContentTypeFromStreamGetDirectoryFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
                        {
                            try {
                                FileInputStream fileInputStream = new FileInputStream(filePath);
                                System.out.println(file.getName() + ": " + URLConnection.guessContentTypeFromStream(new BufferedInputStream(fileInputStream)));
                            } catch (Exception e) {
                                System.out.println(file.getName() + ", File Error type:" + e.toString());
                            }
                        }
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            } else {
                System.out.println(fileOrDirectory.toString() + ": 文件类型未知!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void MagicGetDirectoryFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
                        {
                            /*try {
                                MagicMatch magicMatch = Magic.getMagicMatch(file, false);
                                System.out.println(file.getName() + ": " + magicMatch.getMimeType());
                            } catch (Exception e) {
                                System.out.println(file.getName() + ",File Error type: " + e.toString());
                            }*/
                        }
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            } else {
                System.out.println(fileOrDirectory.toString() + ": 文件类型未知!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    void TikaGetDirectoryFileType() throws Exception {
        try {
            File fileOrDirectory = new File(ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH);
            if (fileOrDirectory.isDirectory()) {
                fileList = fileOrDirectory.list();
                for (int i = 0; i < fileList.length; i++) {
                    String filePath = ProjectDefaultConfig.PROJECT_DEFAULT_AVATAR_PATH + "/" + fileList[i];
                    File file = new File(filePath);
                    if (file.isFile()) {
                        {
                            try {
                                Tika tika = new Tika();
                                String mime = tika.detect(file);
                                System.out.println(file.getName() + ": " + mime);
                            } catch (Exception e) {
                                System.out.println(file.getName() + ",File Error type: " + e);
                            }
                        }
                    } else if (file.isDirectory()) {
                        System.out.println(file.toString() + ": 文件夹!");
                    } else {
                        System.out.println(file.toString() + ": 文件类型未知!");
                    }
                }
            } else if (fileOrDirectory.isFile()) {
                System.out.println(fileOrDirectory.toString() + ": 文件!");
            } else {
                System.out.println(fileOrDirectory.toString() + ": 文件类型未知!");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
