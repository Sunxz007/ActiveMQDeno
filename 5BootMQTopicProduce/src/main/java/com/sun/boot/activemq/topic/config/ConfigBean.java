package com.sun.boot.activemq.topic.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import javax.jms.Topic;

@Configuration
@EnableJms
public class ConfigBean {

    @Value("${myTopic}")
    private String myTopic;

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(myTopic);
    }

}
