package com.e3e4e20.utils;

/*
 * Description: 数据响应对象
 * Created: 2020-04-04 20:37 星期六
 * Author: DreamSnow·Draco
 * Company: none
 * */

/**
 * 数据响应对象
 * {
 * "code": Integer 状态码
 * "info": Boolean 指令执行成功否
 * "message": String 返回信息
 * "data":T {
 * //返回数据
 * }
 * }
 */
public class ResponseData<T> {
    /*
     * 200: success
     * 300: unLogin,没有登录/token认证已过期,后端不能执行后续操作
     * 301: unauthorized,当前登录的用户不具有执行相关的权限,后端不能执行后续的操作
     * 400: failure,前端数据校验不通过,后端不能执行相关操作/无需重复执行相关操作
     * 401: notFound,前端发送的请求,再后端没有匹配的资源或者接口,无法执行后续操作
     * 500: error,检索的数据不存在/已存在,后端无法封装数据
     * */
    private Integer code;
    /*
     * true: 指令执行成功
     * false: 指令执行失败
     */
    private Boolean info;
    /*
     * code 和 info 对应的属性值的文字说明
     * */
    private String message;
    /*
     * 返回给前端的数据
     * */
    private T data;

    private ResponseData(Integer code, Boolean info, String message, T data) {
        this.code = code;
        this.info = info;
        this.message = message;
        this.data = data;
    }

    /**
     * 指令执行成功
     * code 200
     * info true
     *
     * @param message    code 和 info 对应的属性值的文字说明
     * @param resultData 返回给前端的数据
     * @return ResponseData 返回给前端的已经封装好了的数据
     */
    public static <T> ResponseData SUCCESS(String message, T resultData) {
        return new ResponseData(200, true, message, resultData);
    }

    /**
     * 没有登录/token认证已过期,后端不能执行后续操作
     * code 300
     * info false
     * message 请登录!
     * data null
     *
     * @return ResponseData
     */
    public static <T> ResponseData UNVERIFIED() {
        return new ResponseData(300, false, "请登录!", null);
    }

    /**
     * 当前登录的用户不具有执行相关的权限,后端不能执行后续的操作
     * code 301
     * info false
     * message 不具有该操作权限,不能执行相关操作!
     * data null
     *
     * @return ResponseData
     */
    public static <T> ResponseData UNAUTHORIZED(String message) {
        return new ResponseData(300, false, "不具有该操作权限,不能执行相关操作!", null);
    }

    /**
     * 指令执行失败,检索的数据不存在/已存在,后端不能执行相关操作/无需重复执行相关操作
     * code 400
     * info false
     *
     * @param message 错误提示
     * data null
     * @return ResponseData
     */
    public static <T> ResponseData FAILURE(String message) {
        return new ResponseData(400, false, message, null);
    }

    /**
     * 前端发送的请求,再后端没有匹配的资源或者接口,无法执行后续操作
     * code 401
     * info false
     * message 正在访问不存在的资源
     * data null
     * @return ResponseData
     */
    public static <T> ResponseData NOT_FOUND() {
        return new ResponseData(401, false, "正在访问不存在的资源", null);
    }

    /**
     * 抛出异常,前端提供的数据无法通过校验
     * code 500
     * info error
     *
     * @param message 错误提示
     * data null
     * @return ResponseData
     */
    public static <T> ResponseData ERROR(String message) {
        return new ResponseData(500, false, message, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getInfo() {
        return info;
    }

    public void setInfo(Boolean info) {
        this.info = info;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}