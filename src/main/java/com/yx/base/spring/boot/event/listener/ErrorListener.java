package com.yx.base.spring.boot.event.listener;

import com.yx.base.spring.boot.event.PayEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-25 9:51
 */
@Component
public class ErrorListener implements ApplicationListener<PayEvent> {
    @Override
    @Async//加上异步执行的注解
    public void onApplicationEvent(PayEvent payEvent) {
        //订阅主题
        String topic = payEvent.getTopic();
        //消息体
        Map<String, Object> map = payEvent.getSourceMap();
        int a = 10 / 0;
        //发送短信
        System.out.println("订阅主题是:" + topic + ";Error测试:" + map.get("msg"));
    }
}
