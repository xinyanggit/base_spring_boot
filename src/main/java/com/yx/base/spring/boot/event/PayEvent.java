package com.yx.base.spring.boot.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * 进行测试 spring 观察者设计模式
 * Listener<Event>
 *
 * @author yangxin2@cnhnkj.com
 * @create 2020-09-25 9:22
 */
public class PayEvent extends ApplicationEvent {
//继承 事件监听器
    /**
     * 资源
     */
    private Map<String, Object> sourceMap;
    /**
     * 主题
     */
    private String topic;

    public PayEvent(Object source, Map<String, Object> sourceMap, String topic) {
        super(source);
        this.sourceMap = sourceMap;
        this.topic = topic;
    }


    public PayEvent(Object source) {
        super(source);
    }

    public Map<String, Object> getSourceMap() {
        return sourceMap;
    }

    public void setSourceMap(Map<String, Object> sourceMap) {
        this.sourceMap = sourceMap;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

