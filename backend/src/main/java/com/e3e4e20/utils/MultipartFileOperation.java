package com.e3e4e20.utils;

/*
Filename: MultipartFileOperation
Created: 2023年05月19日 15时08分28秒 星期五
Author: 天龙梦雪
*/

import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMimeKeys;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 使用 spring boot 上传的文件 MultipartFile 相关的内容
 * 1. 格式化文件名称,格式为 文件名称.文件后缀名 的形式
 * 2. 判断 MultipartFile 的 mime type
 * 3. 获取 MultipartFile 的 文件后缀名称
 */

public class MultipartFileOperation {
    private final Logger log = LoggerFactory.getLogger("Class: MultipartFileOperation ");
    private final String separator = "/|\\\\";
    // 文件保存目录的绝对路径
    private String fileAbsoluteDirectory;
    // 文件原始名称
    private String fileOriginalName;
    // 文件重命名的文件名称
    private String fileName;
    // 文件保存的绝对路径
    private String fileAbsolutePath;
    // 上传的文件
    private MultipartFile document;

    public MultipartFileOperation(MultipartFile multipartFile, String fileAbsoluteDirectory, String fileName) {
        // 判断上传的文件是否为空
        if (null == multipartFile) {
            log.error("Constructor: 上传的文件不能为空!");
            throw new NullPointerException("文件不能为空!");
        }
        // 将上传的文件赋值给类的属性
        this.setDocument(multipartFile);
        // 将上传的文件的原始名称赋值给类的属性
        this.setFileOriginalName(multipartFile.getOriginalFilename());
        // 将文件的保存目录赋值给类的属性值
        this.setFileAbsoluteDirectory(fileAbsoluteDirectory);
        // 将文件名称赋值给类的属性值
        this.setFileName(fileName);
        // 将文件的绝对路径赋值给类的属性
        this.setFileAbsolutePath(this.getFileAbsoluteDirectory().concat(this.getFileName()));
    }

    /**
     * 计算上传的文件的大小,数值部分要介于(0,1024)
     *
     * @return 文件大小(数值)+单位
     */
    public String calcFileSize() {
        // 文件大小的单位
        final String[] unit = {"B", "KB", "MB", "GB", "TB"};
        // 文件大小单位数组的索引
        int index = 0;
        // 获取文件的字节大小
        long fileSize = this.getDocument().getSize();
        // 文件大小的进制转换
        while (fileSize >= 1024) {
            fileSize /= 1024;
            ++index;
        }
        log.info("calcFileSize: 文件大小: " + fileSize + " " + unit[index]);
        return fileSize + " " + unit[index];
    }

    /**
     * 判断文件的文件类型 mime types
     *
     * @return 文件类型 mime types
     */
    public String getMimeType() {
        // https://blog.csdn.net/weixin_43194885/article/details/109747552
        AutoDetectParser parser = new AutoDetectParser();
        parser.setParsers(new HashMap<>());
        Metadata metadata = new Metadata();
        metadata.add(TikaMimeKeys.MIME_TYPE_MAGIC, this.getDocument().getName());
        try (InputStream stream = this.getDocument().getInputStream()) {
            parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        } catch (Exception exception) {
            log.error("getMimeType: " + exception.getMessage());
            log.error("getMimeType: 无法判断文件 " + this.getFileOriginalName() + " 的文件类型!");
            throw new RuntimeException("无法识别文件类型!");
        }
        return metadata.get(HttpHeaders.CONTENT_TYPE);
    }

    /**
     * 根据文件的 mime type 获取该文件的文件后缀名
     *
     * @return 文件的后缀名 or null
     */
    public String getFileExtension() {
        try {
            MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
            MimeType mimeType = mimeTypes.forName(this.getMimeType());
            log.info("getFileExtension: 文件: " + this.getFileOriginalName() + " 的后缀名称为 " + mimeType.getExtension());
            return mimeType.getExtension();
        } catch (Exception exception) {
            log.error("getFileExtension: " + exception.getMessage());
            log.error("getFileExtension: 文件: " + this.getFileOriginalName() + " 的后缀名称获取失败!");
            throw new RuntimeException("无法判断文件的后缀名!");
        }
    }

    /**
     * 根据文件的 mime type 判断文件是否是一个图片文件
     */
    public void isImageFile() {
        String mimeType = this.getMimeType();
        // mime type 中图片文件的开头以 image/ 开始
        if (!mimeType.contains("image/")) {
            log.error("isImageFile: " + this.getFileAbsolutePath() + " 不是一个图片文件!");
            throw new RuntimeException("非图片文件!");
        } else {
            log.info("isImageFile: " + this.getFileAbsolutePath() + " 是一个图片文件!");
        }
    }

    /**
     * 保存一个 MultipartFile 类型文件在本地
     *
     * @throws IOException IO Error
     */
    public void saveFile() throws IOException {
        log.info("saveFile: 正在将文件 " + this.getFileOriginalName() + " 保存在本地,并将文件重命名为 " + this.getFileName() + " ...");
        // 打开指定文件目录
        File directory = new File(this.getFileAbsoluteDirectory());
        // 若是指定目录不存在,则将该目录创建出来
        if (!directory.exists()) {
            log.info("saveFile: 本地目录 " + this.getFileAbsoluteDirectory() + ",不存在,开始创建该目录...");
            boolean result = directory.mkdir();
            if (!result) {
                log.error("saveFile: 创建目录 " + this.getFileAbsoluteDirectory() + " 失败...");
                throw new IOException("创建目录" + this.getFileAbsoluteDirectory() + " 失败!");
            }
            log.info("saveFile: 本地目录 " + this.getFileAbsoluteDirectory() + " 创建成功...");
        }
        // 将文件保存到指定目录下
        File file = new File(this.getFileAbsolutePath());
        try {
            this.getDocument().transferTo(file);
            log.info("saveFile: 保存文件 " + this.getFileOriginalName() + " 至本地目录 " + this.getFileAbsoluteDirectory() + " 成功...");
            log.info("saveFile: 文件 " + this.getFileOriginalName() + " 已重命名为 " + this.getFileName() + " ...");
            log.info("saveFile: 文件 " + this.getFileName() + " 的大小为 " + this.calcFileSize() + " ...");
        } catch (Exception exception) {
            log.error("saveFile: " + exception.getMessage());
            log.error("saveFile: 保存文件 " + this.getFileOriginalName() + " 至本地目录 " + this.getFileAbsoluteDirectory() + " 失败...");
            throw new IOException("保存文件失败!");
        }
    }

    public String getFileAbsoluteDirectory() {
        return fileAbsoluteDirectory;
    }

    public void setFileAbsoluteDirectory(String fileAbsoluteDirectory) {
        // 判断文件保存的目录是否非空
        if (null == fileAbsoluteDirectory || fileAbsoluteDirectory.equals("")) {
            log.error("setFileAbsoluteDirectory: 文件绝对路径为空,无法读取文件!");
            throw new NullPointerException("文件路径不能为空!");
        }
        /*
        1. 若是文件路径是以 / 开头,那么该路径属于 UNIX 以及类 UNIX 系统文件目录路径
        2. 若是文件路径是以 ?:/ (? 为 26 个字母之一)开头,那么该路径属于 Windows 系统文件目录路径
        3. 若是文件路径是以 ?:\\ (? 为 26 个字母之一)开头,那么该路径属于 Windows 系统文件目录路径
         */
        String filePathSimple;
        if (fileAbsoluteDirectory.startsWith("/")) {
            // UNIX 目录路径
            filePathSimple = fileAbsoluteDirectory.substring(1);
        } else if (fileAbsoluteDirectory.matches("^[a-zA-Z]:/.*") || fileAbsoluteDirectory.matches("^[a-zA-Z]:\\\\.*")) {
            // Windows 目录路径
            filePathSimple = fileAbsoluteDirectory.substring(3);
        } else {
            // 非法路径
            log.error("setFileAbsoluteDirectory: " + fileAbsoluteDirectory + " 不是一个合法的文件路径,不属于UNIX或者Windows系统文件路径!");
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
            log.error("setFileAbsoluteDirectory: " + fileAbsoluteDirectory + " 不是一个合法的文件路径,不应该包含 * ? : \" < > | 这七个字符!");
            throw new NullPointerException("文件路径不合法!");
        }
        // 对文件路径执行二次校验
        // 分割文件路径中的标识符
        String[] strings = filePathSimple.split(this.separator);
        for (String string : strings) {
            if (null == string || string.equals("")) {
                log.error("setFileAbsoluteDirectory: " + fileAbsoluteDirectory + ",不是一个合法的文件路径,其内包含了连续的 / 或者 \\\\");
                throw new NullPointerException("文件路径不合法!");
            }
        }
        log.info("setFileAbsoluteDirectory: " + fileAbsoluteDirectory + " 是一个合法的文件路径!");
        // 保存的目录路径添加分隔符
//        if (!(fileAbsoluteDirectory.endsWith("/") || fileAbsoluteDirectory.endsWith("\\"))) {
//            if (fileAbsoluteDirectory.contains("/")) {
//                fileAbsoluteDirectory = fileAbsoluteDirectory.concat("/");
//            } else if (fileAbsoluteDirectory.contains("\\")) {
//                fileAbsoluteDirectory = fileAbsoluteDirectory.concat("\\");
//            }
//        }
//        this.fileAbsoluteDirectory = fileAbsoluteDirectory;
        // 对目录执行格式化
        this.fileAbsoluteDirectory = this.formatFileAbsoluteDirectory(fileAbsoluteDirectory);
    }

    public String getFileOriginalName() {
        return fileOriginalName;
    }

    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        // 判断文件名称是否为空
        if (null == fileName || fileName.equals("")) {
            log.error("setFileName: 重命名上传的文件的名称为空!");
            throw new NullPointerException("文件重命名的名称不能为空!");
        }
        // 为文件添加文件后缀名
        if (fileName.endsWith(".")) {
            fileName = fileName.substring(0, fileName.length() - 1);
        }
        fileName = fileName.concat(this.getFileExtension());
        this.fileName = fileName;
    }

    public String getFileAbsolutePath() {
        return fileAbsolutePath;
    }

    public void setFileAbsolutePath(String fileAbsolutePath) {
        this.fileAbsolutePath = fileAbsolutePath;
    }

    public MultipartFile getDocument() {
        return document;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }

    /**
     * 格式化一个文件所在目录的绝对路径
     * 1. 一个合法的文件路径不会为空
     * 2. 一个合法的文件路径不会包含 * ? : " < > | 这个七个字符
     * 3. 一个合法的文件路径不会包含连续的 / 或者 \\ 即 // 或者 \\\\
     *
     * @param fileAbsoluteDirectory 文件所在目录的绝对路径
     * @return 一个合法的文件所在目录的绝对路径
     */
    public String formatFileAbsoluteDirectory(String fileAbsoluteDirectory) {
        // 对文件路径分割,格式化出文件所在目录的绝对路径
        String[] strings = fileAbsoluteDirectory.split(this.separator);
        String formatFileAbsoluteDir = "";
        for (String string : strings) {
            formatFileAbsoluteDir = formatFileAbsoluteDir.concat(string);
            formatFileAbsoluteDir = formatFileAbsoluteDir.concat("/");
        }
        log.info("formatFileAbsoluteDirectory: 文件的所在目录的绝对路径为: " + formatFileAbsoluteDir);
        return formatFileAbsoluteDir;
    }

    /**
     * 对文件名称执行格式化
     * 若是文件名称中包含了后缀名部分,则将文件的后缀名称部分去除
     *
     * @param fileName 文件名称
     * @return 一个合法文件名称
     */
    public String formatFileName(String fileName) {
        // 去除文件名称中的后缀名,只保留后缀名之前的部分
        if (fileName.contains(".")) {
            // 以字符 . 拆分后缀名
            String[] strings2 = fileName.split("\\.");
            String formatFileName = "";
            // 重新拼接文件名称
            for (int i = 0; i < strings2.length - 1; i++) {
                formatFileName = formatFileName.concat(strings2[i]);
                formatFileName = formatFileName.concat(".");
            }
            // 去除拼接的文件名称最后面多余的字符 .
            formatFileName = formatFileName.substring(0, formatFileName.length() - 1);
            log.info("formatFileName: 文件名称为: " + formatFileName);
            return formatFileName;
        } else {
            // 若是没有包含字符 . 那么直接作为文件名称使用
            log.info("formatFileName: 文件名称为: " + fileName);
            return fileName;
        }
    }
}
