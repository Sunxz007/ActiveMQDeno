package com.sun.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Jms_TX_Consumer {
    private static final String ACTIVEMQ_URL = "tcp://localhost:61617";
    private static final String ACTIVEMQ_QUEUE_NAME = "Queue-TX";

    public static void main(String[] args) throws JMSException, IOException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // 创建session会话，两个参数transacted=事务,acknowledgeMode=确认模式(签收)
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        Queue queue = session.createQueue(ACTIVEMQ_QUEUE_NAME);
        MessageConsumer messageConsumer = session.createConsumer(queue);
        messageConsumer.setMessageListener(new MessageListener(){
            int a = 0;

            @Override
            public void onMessage(Message message) {
                try {
                    if(null != message && message instanceof TextMessage){
                        TextMessage textMessage=(TextMessage)message;
                        System.out.println("***消费者接收到的消息:   " + textMessage.getText());
                        if (a == 0) {
                            System.out.println("commit");
                            // 消费者开启了事务就必须手动提交，不然会重复消费消息
                            session.commit();
                        } else if (a==2) {
                            System.out.println("rollback");
                            session.rollback();
                        }
                    }
                    a++;
                } catch (JMSException e) {
                    e.printStackTrace();
                    System.out.println("出现异常，消费失败，放弃消费");
                    try {
                        session.rollback();
                    } catch (JMSException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        //关闭资源
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
