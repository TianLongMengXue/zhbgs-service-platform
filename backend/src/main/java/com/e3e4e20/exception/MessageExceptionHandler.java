package com.e3e4e20.exception;

/*
Filename: MessageExceptionHandler
Created: 2023年05月25日 17时22分40秒 星期四
Author: 天龙梦雪
*/

import com.e3e4e20.utils.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理所有使用自定义异常类 MessageException 抛出的异常信息
 * 将异常信息传递给 ResponseData 构造返回给前端的响应
 */
@ControllerAdvice
public class MessageExceptionHandler {
    private final Logger log = LoggerFactory.getLogger("Class: MessageExceptionHandler");

    @ExceptionHandler(value = FailureMessageException.class)
    @ResponseBody
    public ResponseData failureMessageExceptionHandler(Exception exception) {
        return ResponseData.FAILURE(exception.getMessage());
    }

    @ExceptionHandler(value = ErrorMessageException.class)
    @ResponseBody
    public ResponseData errorMessageExceptionHandler(Exception exception) {
        return ResponseData.ERROR(exception.getMessage());
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    @ResponseBody
    public ResponseData unAuthorizedExceptionHandler(Exception exception) {
        return ResponseData.UNAUTHORIZED(exception.getMessage());
    }

    @ExceptionHandler(value = UnLoginException.class)
    @ResponseBody
    public ResponseData unLoginException() {
        return ResponseData.UNLOGIN();
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public ResponseData bindException(Exception exception) {
        BindException bindException = (BindException) exception;
        List<ObjectError> errorList = bindException.getAllErrors();
        ObjectError error = errorList.get(0);
        return ResponseData.FAILURE(error.getDefaultMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseData methodArgumentNotValidException(Exception exception) {
        MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
        List<ObjectError> errorList = methodArgumentNotValidException.getBindingResult().getAllErrors();
        ObjectError error = errorList.get(0);
        return ResponseData.FAILURE(error.getDefaultMessage());
    }
}
