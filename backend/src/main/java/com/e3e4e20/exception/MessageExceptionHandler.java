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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理所有使用自定义异常类 MessageException 抛出的异常信息
 * 将异常信息传递给 ResponseData 构造返回给前端的响应
 */
@RestController
@ControllerAdvice
public class MessageExceptionHandler {
    private final Logger log = LoggerFactory.getLogger("Class: MessageExceptionHandler ");

    @ExceptionHandler(value = FailureMessageException.class)
    public ResponseData failureMessageExceptionHandler(Exception exception) {
        log.error("failureMessageExceptionHandler: " + exception.getMessage());
        return ResponseData.FAILURE(exception.getMessage());
    }

    @ExceptionHandler(value = ErrorMessageException.class)
    public ResponseData errorMessageExceptionHandler(Exception exception) {
        log.error("errorMessageExceptionHandler: " + exception.getMessage());
        return ResponseData.ERROR(exception.getMessage());
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseData unAuthorizedExceptionHandler(Exception exception) {
        log.error("unAuthorizedExceptionHandler: " + exception.getMessage());
        return ResponseData.UNAUTHORIZED(exception.getMessage());
    }

    @ExceptionHandler(value = UnverifiedException.class)
    public ResponseData unverifiedException() {
        log.error("unverifiedException: 未登录错误!");
        return ResponseData.UNVERIFIED();
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseData bindException(Exception exception) {
        BindException bindException = (BindException) exception;
        List<ObjectError> errorList = bindException.getAllErrors();
        ObjectError error = errorList.get(0);
        log.error("bindException: " + error.getDefaultMessage());
        return ResponseData.FAILURE(error.getDefaultMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData methodArgumentNotValidException(Exception exception) {
        MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
        List<ObjectError> errorList = methodArgumentNotValidException.getBindingResult().getAllErrors();
        ObjectError error = errorList.get(0);
        log.error("methodArgumentNotValidException: " + error.getDefaultMessage());
        return ResponseData.FAILURE(error.getDefaultMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseData exception(Exception exception) {
        log.error("exception: " + exception.getMessage());
        return ResponseData.ERROR(exception.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseData notFoundException () {
        log.error("notFoundException: 404 错误!");
        return ResponseData.NOT_FOUND();
    }
}
