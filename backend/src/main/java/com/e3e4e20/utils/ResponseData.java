package com.e3e4e20.utils;

/*
 * Description: 数据响应对象
 * Created: 2020-04-04 20:37 星期六
 * Author: DreamSnow·Draco
 * Company: none
 * */

import java.util.HashMap;
import java.util.Map;

/**
 * 数据响应对象
 *    {
 *      "code": Integer 状态码
 *      "info": Boolean 指令执行成功否
 *      "message": String 返回信息
 *      "data": Object {
 *          //返回数据
 *      }
 *    }
 */
public class ResponseData {
    /*
    * 200: success
    * 300: unLogin,没有登录/token认证已过期,后端不能执行后续操作
    * 301: unauthorized,当前登录的用户不具有执行相关的权限,后端不能执行后续的操作
    * 400: failure,前端数据校验不通过,后端不能执行相关操作/无需重复执行相关操作
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
    private Map<String,Object> data;

    public ResponseData(Integer code, Boolean info, String message, Map<String,Object> data) {
        this.code = code;
        this.info = info;
        this.message = message;
        this.data = data;
    }

    /**
     * 指令执行成功
     * code 200
     * info true
     * @param message  code 和 info 对应的属性值的文字说明
     * @param resultData 返回给前端的数据
     * @return ResponseData 返回给前端的已经封装好了的数据
     */
    public static ResponseData SUCCESS(String message, Object resultData){
        Map<String,Object> data = new HashMap<>();
        data.put("data", resultData);
         return new ResponseData(200, true, message, data);
    }

    /**
     * 没有登录/token认证已过期,后端不能执行后续操作
     * code 300
     * info false
     * message 请登录!
     * data null
     * @return ResponseData
     */
    public static ResponseData UNVERIFIED() {
        Map<String,Object> data = new HashMap<>();
        data.put("data", null);
        return new ResponseData(300,false,"请登录!", data);
    }

    /**
     * 当前登录的用户不具有执行相关的权限,后端不能执行后续的操作
     * code 301
     * info false
     * message 不具有该操作权限,不能执行相关操作!
     * data null
     * @return ResponseData
     */
    public static ResponseData UNAUTHORIZED (String message) {
        Map<String,Object> data = new HashMap<>();
        data.put("data", null);
        return new ResponseData(300,false,"不具有该操作权限,不能执行相关操作!", data);
    }

    /**
     * 指令执行失败,检索的数据不存在/已存在,后端不能执行相关操作/无需重复执行相关操作
     * code 400
     * info false
     * @param message
     * data null
     * @return ResponseData
     */
    public static ResponseData FAILURE(String message) {
        Map<String,Object> data = new HashMap<>();
        data.put("data", null);
        return new ResponseData(400, false, message, data);
    }

    /**
     * 抛出异常,前端提供的数据无法通过校验
     * code 500
     * info error
     * @param message
     * data null
     * @return ResponseData
     */
    public static ResponseData ERROR(String message) {
        Map<String,Object> data = new HashMap<>();
        data.put("data", null);
        return new ResponseData(500, false, message, data);
    }
}
