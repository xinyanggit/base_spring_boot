package com.yx.base.spring.boot.conf;

import com.yx.base.spring.boot.exception.CustomServiceException;
import com.yx.base.spring.boot.result.Result;
import com.yx.base.spring.boot.result.ResultCode;
import com.yx.base.spring.boot.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * 基于注解的    全局异常处理
 *
 * @author yx start
 * @create 2020/3/22,14:28
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //总 针对统一异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        log.info(e.getMessage());
        return ResultGenerator.genFailResult("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员")
                .setCode(ResultCode.INTERNAL_SERVER_ERROR);
    }

    //分1 针对处理NPE
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result error(NullPointerException e) {
        e.printStackTrace();
        return ResultGenerator.genFailResult(e.getMessage());
    }

    //分2 处理我们自定义的异常 CustomServiceException
    @ExceptionHandler(CustomServiceException.class)
    @ResponseBody
    public Result error(CustomServiceException e) {
        e.printStackTrace();
        return ResultGenerator.genFailResult(e.getMessage());
    }

    // 分3 如果需要根据异常渲染界面。
    @ExceptionHandler(value = ServletException.class)
    public ModelAndView myErrorHandler(ServletException ex) {
        ModelAndView modelAndView = new ModelAndView();
        //指定错误页面的模板页 .自己定义
        modelAndView.setViewName("error");
        modelAndView.addObject("code", "");
        modelAndView.addObject("msg", ex.getMessage());
        return modelAndView;
    }

    /**
     * MethodArgumentNotValidException是springBoot中进行绑定参数校验时的异常,需要在springBoot中处理
     * 方法参数 校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResultGenerator.genFailResult(e.getBindingResult().getFieldError().getDefaultMessage());
    }


}
