package com.yx.base.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 985256523@qq.com
 * @data 2020年9月25日10:00:12
 */

@SpringBootApplication
@EnableAsync(proxyTargetClass = true) //proxyTargetClass= true =>DefaultAopProxyFactory 对应，采用cglib 代理 createAopProxy
@EnableKafka
public class BaseSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseSpringBootApplication.class, args);
    }

}
