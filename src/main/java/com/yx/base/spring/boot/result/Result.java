package com.yx.base.spring.boot.result;

import com.alibaba.fastjson.JSON;

/**
 * 接口返回结果封装
 * @author yx start
 * @create 2020/3/19,17:42
 */
public class Result<T> {

    private int code ;
    private String message;
    private T data ;

    public int getCode() {
        return code;
    }

    /**
     * 此处set方法 有变化。需要注意
     * @param resultCode
     * @return
     */
    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this ;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
