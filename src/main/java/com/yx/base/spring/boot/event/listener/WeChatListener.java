package com.yx.base.spring.boot.event.listener;

import com.yx.base.spring.boot.event.PayEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 微信通知
 *
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-25 9:28
 */
@Component
public class WeChatListener implements ApplicationListener<PayEvent> {
    @Override
    public void onApplicationEvent(PayEvent payEvent) {
        //订阅主题
        String topic = payEvent.getTopic();
        //消息体
        Map<String, Object> map = payEvent.getSourceMap();
        //发送短信
        System.out.println("订阅主题是:" + topic + ";微信通知:" + map.get("msg"));
    }
}
