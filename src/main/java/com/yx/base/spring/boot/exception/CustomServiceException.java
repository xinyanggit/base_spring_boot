package com.yx.base.spring.boot.exception;

/**
 * 自定义一个异常，进行处理
 * @author yx start
 * @create 2020/3/21,17:11
 */
public class CustomServiceException extends  RuntimeException {
    public CustomServiceException() {
    }

    public CustomServiceException(String message) {
        super(message);
    }

    public CustomServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
