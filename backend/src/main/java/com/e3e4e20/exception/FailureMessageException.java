package com.e3e4e20.exception;

/*
 * Filename: MessageException
 * Created: 2020-04-06 11:59 星期一
 * Author: 天龙梦雪
 * */


/**
 * 自定义异常类 ==> 继承 Exception
 * 用来抛出一些自定义的的错误,方便构造前端响应
 * 自定义错误 ResultMessage
 * 前端响应 ResponseData
 */
public class FailureMessageException extends Exception {

    public FailureMessageException(String message) {
        super(message);
    }
}
