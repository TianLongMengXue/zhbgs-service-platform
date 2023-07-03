package com.e3e4e20.utils;

import org.apache.tika.Tika;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMimeKeys;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

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

    public FileOperation() {
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
        log.info("fileNameIsLegal: " + fileName + " 是一个合法的文件名称!");
    }

    /**
     * 判断文件的绝对路径是否合法
     * 1. 一个合法目录路径不能为空
     * 2. 一个合法的目录路径不能包含  * ? : " < > | 这个七个字符
     * 3. 一个合法的目录路径不会包含连续的 / 或者 \\ 即 // 或者 \\\\
     * 4. 一个合法的文件名称不能为空
     * 5. 一个合法的文件名称不会包含  / \ * ? : " < > | 这九个字符
     *
     * @param fileFullPath 文件的绝对路径
     */
    public void fileFullPathIsLegal(String fileFullPath) {
        // 将文件的绝对路径分割为文件所在目录的绝对路径和文件的名称
        String[] strings = fileFullPath.split("/|\\\\");
        String fileAbsoluteDirectory = fileFullPath.substring(0,
                (fileFullPath.length() - strings[strings.length - 1].length()));
        // 判断文件名称和文件所在目录是否合法
        this.fileNameIsLegal(strings[strings.length - 1]);
        // 判断文件所在目录是否和发
        this.fileAbsoluteDirectoryIsLegal(fileAbsoluteDirectory);
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
        // 对文件所在目录的绝对路径执行合法性判断
        this.fileAbsoluteDirectoryIsLegal(fileAbsoluteDirectory);
        // 对文件路径分割,格式化出文件所在目录的绝对路径
        String[] strings = fileAbsoluteDirectory.split("/|\\\\");
        String formatFileAbsoluteDir = "";
        for (int i = 0; i < strings.length; i++) {
            formatFileAbsoluteDir = formatFileAbsoluteDir.concat(strings[i]);
            formatFileAbsoluteDir = formatFileAbsoluteDir.concat("/");
        }
        log.info("setFileAbsoluteDirectory: 文件的所在目录的绝对路径为: " + formatFileAbsoluteDir);
        return formatFileAbsoluteDir;
    }

    /**
     * 判断文件名称是否合法
     * 1. 一个合法的文件名称不能为空
     * 2. 一个合法的文件名称不会包含  / \ * ? : " < > | 这九个字符
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
            log.info("setFileName: 文件名称为: " + formatFileName);
            return fileName;
        } else {
            // 若是没有包含字符 . 那么直接作为文件名称使用
            log.info("setFileName: 文件名称为: " + fileName);
            return fileName;
        }
    }

    /**
     * 判断一个本地已存在文件的文件类型,即根据文件的绝对路径判断文件的文件类型
     *
     * @param fileFullPath 文件的绝对路径
     */
    public String getFileType(String fileFullPath) {
        this.fileFullPathIsLegal(fileFullPath);
        File file = new File(fileFullPath);
        String formatName = null;
        try {
            Tika tika = new Tika();
            formatName = tika.detect(file);
            log.info("setFileType:" + fileFullPath + ",文件的类型:" + formatName);
        } catch (Exception exception) {
            log.error("setFileType: ERROR File Type: " + exception.getMessage());
        }
        if (null == formatName || "application/octet-stream".equals(formatName)) {
            log.error("setFileType: 使用Tika获取文件 " + fileFullPath + " 的格式类型失败!");
            throw new NullPointerException("不可识别的文件类型!");
        }
        return formatName;
    }

    /**
     * 判断一个 MultipartFile 类型文件的文件类型
     *
     * @param document MultipartFile 类型文件
     * @return 文件类型MimeType
     */
    public String getFileType(MultipartFile document) {
        // https://blog.csdn.net/weixin_43194885/article/details/109747552
        AutoDetectParser parser = new AutoDetectParser();
        parser.setParsers(new HashMap<MediaType, Parser>());
        Metadata metadata = new Metadata();
        metadata.add(TikaMimeKeys.MIME_TYPE_MAGIC, document.getName());
        try (InputStream stream = document.getInputStream()) {
            parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        } catch (Exception exception) {
            throw new RuntimeException();
        }
        return metadata.get(HttpHeaders.CONTENT_TYPE);
    }

    /**
     * 获取一个本地已存在文件的文件后缀名
     * @param fileFullPath 文件的绝对路径
     * @return 文件的后缀名
     */
    public String getFileExtension(String fileFullPath) {
        try {
            MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
            MimeType mimeType = mimeTypes.forName(this.getFileType(fileFullPath));
            log.info("getFileExtension: 文件: " + fileFullPath + " 的后缀名称为 " + mimeType.getExtension());
            return mimeType.getExtension();
        } catch (Exception exception) {
            log.error("getFileExtension: " + exception.getMessage());
            log.error("getFileExtension: 文件: " + fileFullPath + " 的后缀名称获取失败!");
            return null;
        }
    }

    /**
     * 获取一个 MultipartFile 类型文件的后缀名
     * @param document Multipart 类型文件
     * @return 文件的后缀名称
     */
    public String getFileExtension(MultipartFile document) {
        try {
            MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
            MimeType mimeType = mimeTypes.forName(this.getFileType(document));
            log.info("getFileExtension: 文件: " + fileFullPath + " 的后缀名称为 " + mimeType.getExtension());
            return mimeType.getExtension();
        } catch (Exception exception) {
            log.error("getFileExtension: " + exception.getMessage());
            log.error("getFileExtension: 文件: " + fileFullPath + " 的后缀名称获取失败!");
            return null;
        }
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
        String path = this.formatFileAbsoluteDirectory(filePath);
        String fillFullName = this.formatFileName(fileName) + this.getFileExtension(document);
        log.info("saveFile: 正在新建文件" + path + fillFullName);
        // 计算文件大小
        long fileSize = document.getSize();
        while (fileSize > 1024) {
            fileSize /= 1024;
            ++index;
        }
        log.info("saveFile: 文件" + fillFullName + "的大小为: " + fileSize + " " + unit[index]);
        // 打开指定文件目录
        File directory = new File(path);
        // 若是指定目录不存在,则将该目录创建出来
        if (!directory.exists()) {
            log.info("saveFile: 目录" + path + ",不存在,开始创建...");
            boolean result = directory.mkdir();
            if (!result) {
                log.error("saveFile: 创建目录" + path + "失败...");
                throw new IOException("创建目录" + path + "失败!");
            }
            log.info("saveFile: 目录" + path + ",创建成功...");
        }
        // 将文件保存到指定目录下
        File file = new File(path + fillFullName);
        try {
            document.transferTo(file);
            log.info("saveFile: 创建文件" + path + fillFullName + "成功...");
        } catch (Exception exception) {
            log.error("saveFile: " + exception.getMessage());
            throw new IOException("创建文件" + path + fillFullName + "失败!");
        }
    }
}
