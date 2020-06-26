package com.sun.boot.activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

@Component
public class Queue_Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void produceMsg(){
        // 整合了发送地和发送信息的方法
        jmsMessagingTemplate.convertAndSend(queue,"--------:"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("*****produceMsg task is over*****");
    }

    /**
     * 间隔时间三秒定时投递
     */
    @Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"--------Scheduled:"+ UUID.randomUUID().toString().substring(0,6));
        System.out.println("*****produceMsgScheduled task is over*****");
    }
}
