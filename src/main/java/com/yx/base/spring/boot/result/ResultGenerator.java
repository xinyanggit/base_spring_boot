package com.yx.base.spring.boot.result;

/**
 * 相应结果生成工具
 * @author yx start
 * @create 2020/3/19,17:53
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    /**
     * 直接返回数据
     * @return
     */
    public static  Result genSuccessResult(){
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 传递数据 返回json
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> genSuccessResult(T data){
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    /**
     * 返回失败的数据
     * @param message
     * @return
     */
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

}
