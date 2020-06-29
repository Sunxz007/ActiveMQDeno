package com.sun.boot.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Consumer {
    @JmsListener(destination = "${myTopic}",id = "pri")
    public void receive(TextMessage textMessage){
        try {
            System.out.println("topic消费者接收到的主题信息："+ textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
