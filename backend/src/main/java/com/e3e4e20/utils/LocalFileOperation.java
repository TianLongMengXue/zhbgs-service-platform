package com.e3e4e20.utils;

import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

/*
Filename: FileOperation
Created: 2023年06月30日 09时25分52秒 星期五
Author: 天龙梦雪
*/

/**
 * 文件操作注意事项:
 * 本地已存在的文件提供文件的绝对路径;
 * <p>
 * 文件操作基本功能
 * 1. 判断文件的绝对路径或者文件所在目录路径以及文件名称是否合法;
 * 2. 判断文件的文件类型,即 MIME-TYPE;
 * 4. 将文件转换为 Base64 格式,并转换为可供前端标签使用src="url"中 url 的格式;
 */
public class LocalFileOperation {
    private final Logger log = LoggerFactory.getLogger("Class: LocalFileOperation ");
    // 分割文件路径中的标识符
    private final String separator = "/|\\\\";
    // 文件绝对路径
    private String fileAbsolutePath;
    // 文件的名称
    private String fileName;
    // 文件所在目录的绝对路径
    private String fileAbsoluteDirectory;

    public LocalFileOperation(String fileAbsolutePath) {
        // 判断文件的绝对路径是否为空
        if (null == fileAbsolutePath || fileAbsolutePath.equals("")) {
            log.error("Constructor : 文件的绝对路径为空!");
            throw new NullPointerException("文件路径为空!");
        }
        // 将文件的绝对路径分割为文件所在目录的绝对路径和文件的名称
        String[] strings = fileAbsolutePath.split(separator);
        String fileAbsoluteDirectory = fileAbsolutePath.substring(0,
                (fileAbsolutePath.length() - strings[strings.length - 1].length()));
        // 判断文件名称是否合法,并将合法的文件名称赋值给类的属性
        this.setFileName(strings[strings.length - 1]);
        // 判断文件所在目录是否合法,并将合法的文件所在目录路径赋值给类的属性
        this.setFileAbsoluteDirectory(fileAbsoluteDirectory);
        // 将合法的文件绝对路径赋值给类的属性
        this.setFileAbsolutePath(fileAbsolutePath);
    }

    public String getFileAbsolutePath() {
        return this.fileAbsolutePath;
    }

    public void setFileAbsolutePath(String fileAbsolutePath) {
        this.fileAbsolutePath = fileAbsolutePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileNameIsLegal(fileName);
        this.fileName = fileName;
    }

    public String getFileAbsoluteDirectory() {
        return this.fileAbsoluteDirectory;
    }

    public void setFileAbsoluteDirectory(String fileAbsoluteDirectory) {
        this.fileAbsoluteDirectoryIsLegal(fileAbsoluteDirectory);
        this.fileAbsoluteDirectory = fileAbsoluteDirectory;
    }

    /**
     * 判断文件所在目录的绝对路径是否合法
     * 1. 一个合法目录路径不能为空
     * 2. 一个合法的目录路径不能包含  * ? : " < > | 这个七个字符
     * 3. 一个合法的目录路径不会包含连续的 / 或者 \\ 即 // 或者 \\\\
     *
     * @param filePath 文件所在目录的绝对路径
     */
    public void fileAbsoluteDirectoryIsLegal(String filePath) {
        // 判断非空
        if (null == filePath || filePath.equals("")) {
            log.error("fileAbsoluteDirectoryIsLegal: 文件绝对路径为空,无法读取文件!");
            throw new NullPointerException("文件路径不能为空!");
        }
        /*
        1. 若是文件路径是以 / 开头,那么该路径属于 UNIX 以及类 UNIX 系统文件目录路径
        2. 若是文件路径是以 ?:/ (? 为 26 个字母之一)开头,那么该路径属于 Windows 系统文件目录路径
        3. 若是文件路径是以 ?:\\ (? 为 26 个字母之一)开头,那么该路径属于 Windows 系统文件目录路径
         */
        String filePathSimple;
        if (filePath.startsWith("/")) {
            // UNIX 目录路径
            filePathSimple = filePath.substring(1);
        } else if (filePath.matches("^[a-zA-Z]:/.*") || filePath.matches("^[a-zA-Z]:\\\\.*")) {
            // Windows 目录路径
            filePathSimple = filePath.substring(3);
        } else {
            // 非法路径
            log.error("fileAbsoluteDirectoryIsLegal: " + filePath + " 不是一个合法的文件路径,不属于UNIX或者Windows系统文件路径!");
            throw new NullPointerException("文件路径不合法!");
        }
        // 若是一个文件路径包含了 * ? : " < > | 表示这不是一个合法的路径
        if (filePathSimple.contains("\"")
                || filePathSimple.contains("*")
                || filePathSimple.contains("?")
                || filePathSimple.contains(":")
                || filePathSimple.contains("<")
                || filePathSimple.contains(">")
                || filePathSimple.contains("|")) {
            log.error("fileAbsoluteDirectoryIsLegal: " + filePath + " 不是一个合法的文件路径,不应该包含 * ? : \" < > | 这七个字符!");
            throw new NullPointerException("文件路径不合法!");
        }
        // 对文件路径执行二次校验
        String[] strings = filePathSimple.split(this.separator);
        for (String string : strings) {
            if (null == string || string.equals("")) {
                log.error("fileAbsoluteDirectoryIsLegal: " + filePath + ",不是一个合法的文件路径,其内包含了连续的 / 或者 \\\\");
                throw new NullPointerException("文件路径不合法!");
            }
        }
        log.info("fileAbsoluteDirectoryIsLegal: " + filePath + " 是一个合法的文件路径!");
    }

    /**
     * 判断文件名称是否合法
     * 1. 一个合法的文件名称不能为空
     * 2. 一个合法的文件名称不会包含  / \ * ? : " < > | 这九个字符
     *
     * @param fileName 文件名称
     */
    public void fileNameIsLegal(String fileName) {
        // 判断非空
        if (null == fileName || fileName.equals("")) {
            log.error("fileNameIsLegal: 文件名称为空,无法判断!");
            throw new NullPointerException("文件名称不能为空!");
        }
        // 若是文件名称包含了 / \ * ? : " < > | 表示这不是一个合法的文件名称
        if (fileName.contains("/")
                || fileName.contains("\\")
                || fileName.contains("\"")
                || fileName.contains("*")
                || fileName.contains("?")
                || fileName.contains(":")
                || fileName.contains("<")
                || fileName.contains(">")
                || fileName.contains("|")) {
            log.error("fileNameIsLegal: 一个合法的文件名中,不应该包含 / \\ * ? : \" < > | 这九个字符!");
            throw new RuntimeException("文件名称不合法!");
        }
        log.info("fileNameIsLegal: " + fileName + " 是一个合法的文件名称!");
    }

    /**
     * 判断文件的文件类型 Mime types
     */
    public String getMimeType() {
        File file = new File(this.getFileAbsolutePath());
        String formatName = null;
        try {
            Tika tika = new Tika();
            formatName = tika.detect(file);
            log.info("getMimeType: " + this.getFileAbsolutePath() + ",文件的类型: " + formatName);
        } catch (Exception exception) {
            log.error("getMimeType: ERROR File Type: " + exception.getMessage());
        }
        if (null == formatName || "application/octet-stream".equals(formatName)) {
            log.error("getMimeType: 使用Tika获取文件 " + this.getFileAbsolutePath() + " 的格式类型失败!");
            throw new RuntimeException("不可识别的文件类型!");
        }
        return formatName;
    }

    /**
     * 根据一个本地已存在文件的 mime type 获取文件后缀名
     *
     * @return 文件的后缀名
     */
    public String getFileExtension() {
        try {
            MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
            MimeType mimeType = mimeTypes.forName(this.getMimeType());
            log.info("getFileExtension: 文件: " + this.getFileAbsolutePath() + " 的后缀名称为 " + mimeType.getExtension());
            return mimeType.getExtension();
        } catch (Exception exception) {
            log.error("getFileExtension: " + exception.getMessage());
            log.error("getFileExtension: 文件: " + this.getFileAbsolutePath() + " 的后缀名称获取失败!");
            return null;
        }
    }

    /**
     * 根据一个本地已存在文件的 mime type 判断文件是否是一个图片文件
     */
    public void isImageFile() {
        String mimeType = this.getMimeType();
        // 图片文件的 mime type 是以 image/ 开头
        if (!mimeType.startsWith("image/")) {
            log.error("isImageFile: " + this.getFileAbsolutePath() + " 不是一个图片文件!");
            throw new RuntimeException("非图片文件!");
        } else {
            log.info("isImageFile: " + this.getFileAbsolutePath() + " 是一个图片文件!");
        }
    }

    /**
     * 计算上传的文件的大小,数值部分要介于(0,1024)
     *
     * @return 文件大小(数值)+单位
     */
    public String calcFileSize(int fileSize) {
        // 文件大小的单位
        final String[] unit = {"B", "KB", "MB", "GB", "TB"};
        // 文件大小单位数组的索引
        int index = 0;
        // 文件大小的进制转换
        while (fileSize >= 1024) {
            fileSize /= 1024;
            ++index;
        }
        log.info("calcFileSize: 文件大小: " + fileSize + " " + unit[index]);
        return fileSize + " " + unit[index];
    }

    /**
     * 根据文件的绝对路径转换为 base64 格式的字符串
     *
     * @return base64 格式的字符串
     */
    public String getFileBase64Encoder() {
        byte[] data;
        try {
            // 读取文件
            File file = new File(this.getFileAbsolutePath());
            log.info("getFileBase64Encoder: 读取文件: " + file);
            InputStream inputStream = new FileInputStream(file);
            data = new byte[inputStream.available()];
            log.info("getFileBase64Encoder: 文件的字节大小: " + this.calcFileSize(inputStream.available()));
            int result = inputStream.read(data);
            log.info("getFileBase64Encoder: 读取的文件字节大小: " + this.calcFileSize(result));
            inputStream.close();
        } catch (Exception exception) {
            log.error("getFileBase64Encoder: " + exception.getMessage());
            return null;
        }
        // 对数组进行Base64转码,得到Base64编码的字符串
        Base64.Encoder encoder = Base64.getEncoder();
        String base64encoder = encoder.encodeToString(data);
        log.info("getFileBase64Encoder:" + this.getFileAbsolutePath() + " 文件的Base64编码: " + base64encoder);
        return base64encoder;
    }

    /**
     * HTML 元素属性 src 应填入的 base64 格式编码
     *
     * @return data:image/imageType;base64,base64 格式编码
     */
    public String getFileSrcUrl() {
        // 获取图片的 base64 编码
        String fileBase64Encoder = this.getFileBase64Encoder();
        // 获取图片的类型
        String mimeType = this.getMimeType();
        String prefix = "data:" + mimeType + ";base64,";
        log.info("getFileSrcUrl: 图片的base64编码前缀: " + prefix);
        // 拼接出前端展示的 src 的url
        String imageSrcURL = prefix + fileBase64Encoder;
        log.info("getFileSrcUrl: 前端图片展示的src的url: " + imageSrcURL);
        return imageSrcURL;
    }
}
