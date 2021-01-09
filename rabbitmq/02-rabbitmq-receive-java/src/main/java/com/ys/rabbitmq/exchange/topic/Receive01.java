package com.ys.rabbitmq.exchange.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive01 {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("yaosen..");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            /**
             * topic类型的交换机也是一对多的一种交换机类型，他和fanout都能实现一个消息同时发送给多个队列
             * fanout更适合于使用在一个功能不同的进程来获取数据，例如手机App中的消息推送，一个App可能会有很多个用户来进行安装，然后他们都会启动一个随机的队列来接收自己需要的数据
             * topic更适合不同的功能模块来接收同一个消息，例如商城下单成功后，需要发送消息到队列中。例如RoutingKey为order.success,物流系统监听订单order.* ; 发票系统监听order.*
             * topic 可以使用随机的队列名也可以使用一个明确的队列名，但是如果应用在和订单有关的功能中，建议是有个明确的队列名并且要求为持久化的队列
             */
            channel.queueDeclare("topicQueue01",true,false,false,null);
            channel.exchangeDeclare("topicExchange", "topic", true);//声明交换机
            channel.queueBind("topicQueue01", "topicExchange", "aa");

            channel.basicConsume("topicQueue01",true,new DefaultConsumer(channel) {
                /**
                 *回调方法：消息的具体接收和处理，监听某个队列并获取队列中的数据，
                 *  注意：当前被监听的队列必须已经存在并正确的绑定在某个交换机上
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "utf-8");
                    System.out.println("01接收到的消息aa-->"+message);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
