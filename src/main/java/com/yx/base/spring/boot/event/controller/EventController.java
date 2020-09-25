package com.yx.base.spring.boot.event.controller;

import com.google.common.collect.Maps;
import com.yx.base.spring.boot.event.PayEvent;
import com.yx.base.spring.boot.event.SpringContextUtil;
import com.yx.base.spring.boot.result.Result;
import com.yx.base.spring.boot.result.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-25 9:33
 */
@RestController
@Api("测试事件发布")
public class EventController {


    @GetMapping("/test/event")
    @ApiOperation(value = "测试事件发布订阅", notes = "测试事件发布订阅")
    public Result<Boolean> testEventListener() {
        Map map = new HashMap(1);
        map.put("msg", "测事件");
        //创建一个支付事件
        PayEvent payEvent = new PayEvent(this, map, "支付");
        //获取Spring的ApplicationContext容器，发布事件，监听类监听到事件后就会发送消息
        SpringContextUtil.getApplicationContext().publishEvent(payEvent);
        return ResultGenerator.genSuccessResult(true);
    }

}
