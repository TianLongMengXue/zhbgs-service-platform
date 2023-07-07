package com.e3e4e20.utils;

/*
Filename: Base64Encoder
Created: 2023年05月19日 15时08分28秒 星期五
Author: 天龙梦雪
*/

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Base64;

/**
 * 将文图片转换为base64编码
 */

public class ImageToBase64 {
    private final Logger log = LoggerFactory.getLogger("Class: Base64Encoder ");
    private String imagePath;
    private String imageType;
    private String imageBase64Encoder;

    /**
     * 根据图片的绝对路径,获取图片的文件类型以及将图片转换为 base64 格式
     *
     * @param imagePath 图片的绝对路径
     */
    public ImageToBase64(String imagePath) {
        if (null == imagePath) {
            throw new NullPointerException();
        }
        String[] strings = imagePath.split("/");
        String imageName = strings[strings.length - 1];
        this.imagePath = imagePath;
        log.info("getImagePath:" + imageName + ",图片所在的绝对路径:" + imagePath);
        this.imageType = this.getImageType();
        this.imageBase64Encoder = this.getImageBase64Encoder();
    }

    /**
     * 根据图片的绝对路径,获取图片的文件类型以及将图片转换为 base64 格式
     * @param imageDirectory 图片存储的目录
     * @param imageName 图片的文件名称
     */
    public ImageToBase64(String imageDirectory, String imageName) {
        if (null == imageDirectory || null == imageName) {
            throw new NullPointerException();
        }
        this.imagePath += imageDirectory + imageName;
        log.info("getImagePath:" + imageName + ",图片所在的绝对路径:" + this.imagePath);
        this.imageType = this.getImageType();
        this.imageBase64Encoder = this.getImageBase64Encoder();
    }

    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * 将图片转换为 base64 格式的字符串
     *
     * @return base64 格式的字符串
     */
    public String getImageBase64Encoder() {
        byte[] data;
        String imagePath = getImagePath();
        try {
            // 读取文件
            File file = new File(imagePath);
            log.info("getImageBase64Encoder:读取图片文件:" + file);
            InputStream inputStream = new FileInputStream(file);
            data = new byte[inputStream.available()];
            log.info("getImageBase64Encoder:图片的字节大小:" + inputStream.available());
            int result = inputStream.read(data);
            log.info("getImageBase64Encoder:读取的图片字节大小:" + result);
            inputStream.close();
        } catch (Exception exception) {
            log.error("getImageBase64Encoder: " + exception.getMessage());
            return null;
        }
        // 对数组进行Base64转码,得到Base64编码的字符串
        Base64.Encoder encoder = Base64.getEncoder();
        String base64encoder = encoder.encodeToString(data);
        log.info("imageToBase64Encoder:" + imagePath + ",图片的Base64编码:" + base64encoder);
        return base64encoder;
    }

    /**
     * 根据图片绝对路径获取图片的格式类型
     *
     * @return 图片的格式类型 || null
     */
    public String getImageType() {
        String imagePath = getImagePath();
        File file = new File(imagePath);
        String formatName = null;
        try {
            Tika tika = new Tika();
            formatName = tika.detect(file);
            log.info("getImageType:" + imagePath + ",图片的类型:" + formatName);
        } catch (Exception exception) {
            log.error("getImageType: ERROR File Type: " + exception.getMessage());
        }
        if (null == formatName || "application/octet-stream".equals(formatName)) {
            log.error("getImageType:使用Tika获取图片" + imagePath + "的格式类型失败!使用文件后缀名作为格式类型!");
            String[] strings = imagePath.split("\\.");
            formatName = "image/" + strings[strings.length - 1];
        }
        return formatName;
    }

    /**
     * HTML img 属性 src 应填入的 base64 格式图片编码
     *
     * @return data:image/imageType;base64,base64 格式图片编码
     */
    public String getImageSrcUrl() {
        // 获取图片的类型
        String imageType = this.imageType;
        String prefix = "data:" + imageType + ";base64,";
        log.info("getImageDataUrl: 图片的base64编码前缀: " + prefix);
        // 获取图片的 base64 编码
        String imageBase64Encoder = this.imageBase64Encoder;
        // 拼接出前端展示的img src的url
        String imageSrcURL = prefix + imageBase64Encoder;
        log.info("imageToBase64Encoder: 前端图片展示的src的url: " + imageSrcURL);
        return imageSrcURL;
    }
}
