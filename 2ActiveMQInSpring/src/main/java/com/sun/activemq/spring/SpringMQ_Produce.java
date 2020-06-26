package com.sun.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Service
public class SpringMQ_Produce{

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringMQ_Produce produce = ioc.getBean(SpringMQ_Produce.class);
//        produce.jmsTemplate.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//
//                return session.createTextMessage("spring与activemq的整合case");
//            }
//        });

        produce.jmsTemplate.send((session)-> session.createTextMessage("*****spring与activemq的整合case"));
        System.out.println("*****send task over");
    }
}
