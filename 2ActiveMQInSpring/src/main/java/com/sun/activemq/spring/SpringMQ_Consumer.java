package com.sun.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringMQ_Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Consumer consumer = ioc.getBean(SpringMQ_Consumer.class);
        String retValue =(String) consumer.jmsTemplate.receiveAndConvert();
        System.out.println("******消费者接受到消息："+retValue);
    }
}