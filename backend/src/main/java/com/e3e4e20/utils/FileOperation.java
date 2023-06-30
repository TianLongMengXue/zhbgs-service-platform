package com.e3e4e20.utils;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
Filename: FileOperation
Created: 2023年06月30日 09时25分52秒 星期五
Author: 天龙梦雪
*/

/**
 * 文件操作注意事项:
 * 1. 本地已存在的文件需要提供文件的绝对路径;
 * 2. 非本地已存在的文件需要提供文件所在目录以及文件名称.
 * <p>
 * 文件操作基本功能
 * 1. 判断文件的绝对路径或者文件所在目录路径以及文件名称是否合法;
 * 2. 判断文件的文件类型,即 MIME-TYPE;
 * 3. 保存文件(对文件的保存目录路径和文件名称进行合法校验、格式化);
 * 4. 将文件转换为 Base64 格式,并转换为可供前端标签使用src="url"中 url 的格式;
 */
public class FileOperation {
    private final Logger log = LoggerFactory.getLogger("Class: FileOperation ");
    // 分割文件路径中的标识符
    private final String separator = "/|\\\\";
    // 文件大小的单位
    private final String[] unit = {"B", "KB", "MB", "GB", "TB"};
    // 文件大小单位数组的索引
    private int index = 0;
    // 文件所在的目录
    private String fileAbsoluteDirectory;
    // 文件的名称
    private String fileName;
    // 文件类型
    private String fileType;
    // 文件名称+文件类型
    private String fileFullName;
    // 文件绝对路径
    private String fileFullPath;


    /**
     * 根据文件的绝对路径初始化本类
     * 1.获取文件名称
     * 2.获取文件绝对路径
     * 3.获取文件所在目录
     * 4.调用文件类型函数判断文件类型
     * 5.获取文件名称+文件类型
     *
     * @param fileFullPath 文件的绝对路径
     */
    public FileOperation(String fileFullPath) {
        // 将文件的绝对路径分割为文件所在目录的绝对路径和文件的名称
        String[] strings = fileFullPath.split("/|\\\\");
        String fileAbsoluteDirectory = fileFullPath.substring(0,
                (fileFullPath.length() - strings[strings.length - 1].length()));
        this.setFileAbsoluteDirectory(fileAbsoluteDirectory);
        this.setFileName(strings[strings.length - 1]);
        this.setFileType(fileFullPath);
    }

    /**
     * 根据文件所在的目录和文件名称初始化本类
     *
     * @param fileAbsoluteDirectory 文件所在的目录
     * @param fileName              文件名称
     */
    public FileOperation(String fileAbsoluteDirectory, String fileName) {
        this.setFileAbsoluteDirectory(fileAbsoluteDirectory);
        this.setFileName(fileName);
        if (fileAbsoluteDirectory.endsWith("/")) {
            this.setFileType(fileAbsoluteDirectory.concat(fileName));
        } else {
            this.setFileType(fileAbsoluteDirectory.concat("/").concat(fileName));
        }
    }

    /**
     * 判断文件路径是否合法
     * 1. 一个合法文件路径不能为空
     * 2. 一个合法的文件路径不能包含  * ? : " < > | 这个七个字符
     * 3. 一个合法的文件路径不会包含连续的 / 或者 \\ 即 // 或者 \\\\
     * 4. 一个合法的文件路径指向的文件必须在本地能够找到并被读取
     *
     * @param filePath 文件的绝对路径或者文件所在目录的绝对路径
     */
    public void fileFullPathIsLegal(String filePath) {
        // 判断非空
        if (null == filePath || filePath.equals("")) {
            log.error("fileFullPathIsLegal: 文件绝对路径为空,无法读取文件!");
            throw new NullPointerException("文件路径不能为空!");
        }
        // 若是一个文件路径包含了 * ? : " < > | 表示这不是一个合法的路径
        if (filePath.contains("\"")
                || filePath.contains("*")
                || filePath.contains("?")
                || filePath.contains(":")
                || filePath.contains("<")
                || filePath.contains(">")
                || filePath.contains("|")) {
            log.error("fileFullPathIsLegal: " + filePath + ",不是一个合法的文件路径,不应该包含 * ? : \" < > | 这七个字符!");
            throw new NullPointerException("文件路径不合法!");
        }
        // 对文件路径执行二次校验
        String[] strings = filePath.split(this.separator);
        for (String string : strings) {
            if (null == string || string.equals("")) {
                log.error("fileFullPathIsLegal: " + filePath + ",不是一个合法的文件路径,其内包含了连续的 / 或者 \\\\");
                throw new NullPointerException("文件路径不合法!");
            }
        }
        log.info("fileFullPathIsLegal: " + filePath + " 是一个合法的文件路径!");
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
            throw new NullPointerException("文件名称不合法!");
        }
        log.info("fileNameIsLegal: "+fileName+" 是一个合法的文件名称!");
    }

    /**
     * 判断文件所在目录的绝对路径是否合法
     * 1. 一个合法的文件路径不会为空
     * 2. 一个合法的文件路径不会包含 * ? : " < > | 这个七个字符
     * 3. 一个合法的文件路径不会包含连续的 / 或者 \\ 即 // 或者 \\\\
     *
     * @param fileAbsoluteDirectory 文件所在目录的绝对路径
     */
    public void setFileAbsoluteDirectory(String fileAbsoluteDirectory) {
        // 判断非空
        if (null == fileAbsoluteDirectory || fileAbsoluteDirectory.equals("")) {
            log.error("setFileAbsoluteDirectory: 文件所在目录为空,无法执行初始化!");
            throw new NullPointerException("文件路径不合法!");
        }
        // 若是一个文件路径包含了 * ? : " < > | 表示这不是一个合法的路径
        if (fileAbsoluteDirectory.contains("\"")
                || fileAbsoluteDirectory.contains("*")
                || fileAbsoluteDirectory.contains("?")
                || fileAbsoluteDirectory.contains(":")
                || fileAbsoluteDirectory.contains("<")
                || fileAbsoluteDirectory.contains(">")
                || fileAbsoluteDirectory.contains("|")) {
            log.error("setFileAbsoluteDirectory: " + fileAbsoluteDirectory + ",不是一个合法的文件路径,不应该包含 * ? : \" < > | 这七个字符!");
            throw new NullPointerException("文件路径不合法!");
        }
        // 对文件路径执行二次校验和分割,格式化出文件所在目录的绝对路径
        String[] strings = fileAbsoluteDirectory.split("/|\\\\");
        this.fileAbsoluteDirectory = "";
        for (int i = 0; i < strings.length; i++) {
            if (null == strings[i] || strings[i].equals("")) {
                log.error("setFileAbsoluteDirectory: " + fileAbsoluteDirectory + ",不是一个合法的文件路径,期内包含了连续的 / 或者 \\\\");
                throw new NullPointerException("文件路径不合法!");
            }
            this.fileAbsoluteDirectory = this.fileAbsoluteDirectory.concat(strings[i]);
            this.fileAbsoluteDirectory = this.fileAbsoluteDirectory.concat("/");
        }
        log.info("setFileAbsoluteDirectory: 文件的所在目录的绝对路径为: " + this.fileAbsoluteDirectory);
    }

    /**
     * 判断文件名称是否合法
     * 1. 一个合法的文件名称不能为空
     * 2. 一个合法的文件名称不会包含  / \ * ? : " < > | 这九个字符
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        // 判断非空
        if (null == fileName || fileName.equals("")) {
            log.error("setFileName: 文件名称为空,无法执行初始化!");
            throw new NullPointerException("文件路径不合法!");
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
            log.error("setFileName: 一个合法的文件名中,不应该包含 / \\ * ? : \" < > | 这九个字符!");
            throw new NullPointerException("文件名称不合法!");
        }
        // 去除文件名称中的后缀名,只保留后缀名之前的部分
        if (fileName.contains(".")) {
            // 以字符 . 拆分后缀名
            String[] strings2 = fileName.split("\\.");
            this.fileName = "";
            // 重新拼接文件名称
            for (int i = 0; i < strings2.length - 1; i++) {
                this.fileName = this.fileName.concat(strings2[i]);
                this.fileName = this.fileName.concat(".");
            }
            // 去除拼接的文件名称最后面多余的字符 .
            this.fileName = this.fileName.substring(0, this.fileName.length() - 1);
        } else {
            // 若是没有包含字符 . 那么直接作为文件名称使用
            this.fileName = fileName;
        }
        log.info("setFileName: 文件名称为: " + this.fileName);
    }

    /**
     * 判断一个本地文件的文件类型,即根据文件的绝对路径判断文件的文件类型
     *
     * @param fileFullPath 文件的绝对路径
     */
    public String getFileType(String fileFullPath) {
        File file = new File(fileFullPath);
        String formatName = null;
        try {
            Tika tika = new Tika();
            formatName = tika.detect(file);
            log.info("setFileType:" + fileFullPath + ",图片的类型:" + formatName);
        } catch (Exception exception) {
            log.error("setFileType: ERROR File Type: " + exception.getMessage());
        }
        if (null == formatName || "application/octet-stream".equals(formatName)) {
            log.error("setFileType: 使用Tika获取文件 " + fileFullPath + " 的格式类型失败!");
            throw new NullPointerException("不可识别的文件类型!");
        }
        return formatName;
    }

    public String getFileType(MultipartFile document) {
      // https://blog.csdn.net/weixin_43194885/article/details/109747552
    }

    /**
     * 保存一个 MultipartFile 类型文件在本地
     *
     * @param document MultipartFile 类型文件
     * @param fileName 文件的名称
     * @param filePath 文件保存的目录
     * @throws IOException IO Error
     */
    public void saveFile(MultipartFile document, String fileName, String filePath) throws IOException {
        log.info("saveFile: 正在新建文件" + filePath + fileName);
        // 计算文件大小
        long fileSize = document.getSize();
        while (fileSize > 1024) {
            fileSize /= 1024;
            ++index;
        }
        log.info("saveFile: 文件" + fileName + "的大小为: " + fileSize + " " + unit[index]);
        // 打开指定文件目录
        File directory = new File(filePath);
        // 若是指定目录不存在,则将该目录创建出来
        if (!directory.exists()) {
            log.info("saveFile: 目录" + filePath + ",不存在,开始创建...");
            boolean result = directory.mkdir();
            if (!result) {
                log.error("saveFile: 创建目录" + filePath + "失败...");
                throw new IOException("创建目录" + filePath + "失败!");
            }
        }
        // 将文件保存到指定目录下
        File file = new File(filePath + fileName);
        try {
            document.transferTo(file);
            log.info("saveFile: 创建文件" + filePath + fileName + "成功...");
        } catch (Exception exception) {
            log.error("saveFile: " + exception.getMessage());
            throw new IOException("创建文件" + filePath + fileName + "失败!");
        }
    }
}
