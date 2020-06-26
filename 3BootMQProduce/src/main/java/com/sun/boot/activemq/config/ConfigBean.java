package com.sun.boot.activemq.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;


@Component
@EnableJms // 开启jms适配
public class ConfigBean {

    // 注入配置文件中的 myqueue
    @Value("${myQueue}")
    private String myQueue;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(myQueue);
    }
}
