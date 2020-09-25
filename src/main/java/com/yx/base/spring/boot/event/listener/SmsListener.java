package com.yx.base.spring.boot.event.listener;

import com.yx.base.spring.boot.event.PayEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 短信通知
 *
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-25 9:26
 */
@Component
public class SmsListener implements ApplicationListener<PayEvent> {

    @Override
    public void onApplicationEvent(PayEvent payEvent) {
        //订阅主题
        String topic = payEvent.getTopic();
        //消息体
        Map<String, Object> map = payEvent.getSourceMap();
        //发送短信
        System.out.println("订阅主题是:" + topic + ";发送短信:" + map.get("msg"));
    }
}

