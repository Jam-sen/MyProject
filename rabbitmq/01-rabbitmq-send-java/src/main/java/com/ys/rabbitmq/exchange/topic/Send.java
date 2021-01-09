package com.ys.rabbitmq.exchange.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");//指定host
        connectionFactory.setPort(5672);//指定端口
        connectionFactory.setUsername("root");//指定用户名
        connectionFactory.setPassword("yaosen..");//指定密码
        Connection connection = null;//定义连接
        Channel channel = null;//定义通道
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            /**
             * 由于使用Fanout类型的交换机，因此消息的接收方肯能会有多个因此不建议在消息发送时来创建队列
             * 以及绑定交换机，建议在消费者中创建队列并绑定交换机
             * 但是发送消息时至少应该确保交换机时存在
             */
            channel.exchangeDeclare("topicExchange", "topic", true);

            /**
             * 发布消息到队列
             *  参数1：交换机名称
             *  参数2：消息的RoutingKey，如果这个消息的RoutingKey和某个队列与交换机绑定的BindingKey一致，那么这个消息就会发送到指定的队列中
             *  注意：
             *      1、发送消息时必须确保交换机已经创建并且已经正确的绑定到了某个队列上
             */
            String message = "topic交换机的测试消息";
            channel.basicPublish("topicExchange", "aa.bb.cc", null, message.getBytes("utf-8"));
            System.out.println("消息发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
