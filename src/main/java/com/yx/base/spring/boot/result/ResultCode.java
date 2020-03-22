package com.yx.base.spring.boot.result;

/**
 * 枚举类型封装code
 *
 * @author yx start
 * @create 2020/3/19,17:44
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401),
    /**
     * 接口不存在
     */
    NOT_FOUND(404),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500),
    /***
     * 空指针异常
     */
    NULL_POINT(500);



    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
