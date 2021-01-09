package com.ys.rabbitmq.exchange.direct;

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
            //声明队列
            channel.queueDeclare("myDirectQueue", true, false, false, null);
            /**
             * 声明一个交换机
             * 参数1：交换机的名称，取值任意
             * 参数2：交换机类型，取值为direct、fanout、topic、headers(效率低，不用)
             * 参数3：是否为持久化的交换机
             *  注意：
             *      1、声明交换机时，如果这个交换机已经存在，则会放弃声明，如果不存在则会声明
             *      2、这行代码是可有可无的，但是在使用前必须要确保这个交换机被声明
             */
            channel.exchangeDeclare("directExchange", "direct", true);
            /**
             * 将队列绑定到交换机，
             * 参数1：队列的名称
             * 参数2：交换机名称
             * 参数3：队列的RoutingKey(BindingKey)
             *  注意：
             *      1、在进行队列和交换机绑定时必须要确保交换机和队列已经成功声明
             */
            channel.queueBind("myDirectQueue", "directExchange", "directRoutingKey");
            /**
             * 发布消息到队列
             *  参数1：交换机名称
             *  参数2：消息的RoutingKey，如果这个消息的RoutingKey和某个队列与交换机绑定的BindingKey一致，那么这个消息就会发送到指定的队列中
             *  注意：
             *      1、发送消息时必须确保交换机已经创建并且已经正确的绑定到了某个队列上
             */
            String message = "direct交换机的测试消息";
            channel.basicPublish("directExchange", "directRoutingKey", null, message.getBytes("utf-8"));
            System.out.println("消息发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
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
