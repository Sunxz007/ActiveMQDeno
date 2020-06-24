package com.sun.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsumer {

        //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
        public static final String ACTIVEMQ_URL = "tcp://localhost:61617";
        // 目的地的名称
        public static final String QUEUE_NAME = "jdbc01";

        public static void main(String[] args) throws Exception {
            // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码。
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("admin", "admin", ACTIVEMQ_URL);
            // 2 通过连接工厂，获得连接 connection 并启动访问。
            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
            // 3 创建会话session 。第一参数是是否开启事务， 第二参数是消息签收的方式
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 4 创建目的地（两种 ：队列/主题）。Destination是Queue和Topic的父类
            Queue queue = session.createQueue(QUEUE_NAME);
            // 5. 创建消费者
            MessageConsumer messageConsumer = session.createConsumer(queue);
         // 6. 消费者接受消息 同步阻塞的方式
            while (true) {
                // 始终接受消息
                //TextMessage textMessage=(TextMessage) messageConsumer.receive();
                // 在规定时间内接受消息
                TextMessage textMessage = (TextMessage) messageConsumer.receive(4000L);
                if (null != textMessage) {
                    System.out.println("***消费者接受消息****"+ textMessage.getText());
                }else {
                    break;
                }
            }

            // 关闭资源
            messageConsumer.close();
            session.close();
            connection.close();
        }
}
