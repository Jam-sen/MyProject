package com.ys.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        /**
         *配置rabbitMQ的连接相关信息
         */
        connectionFactory.setHost("localhost");//指定host
        connectionFactory.setPort(5672);//指定端口
        connectionFactory.setUsername("root");//指定用户名
        connectionFactory.setPassword("yaosen..");//指定密码
        Connection connection = null;//定义连接
        Channel channel = null;//定义通道
        try {
            connection = connectionFactory.newConnection();//获取连接
            channel = connection.createChannel();//获取信道
            /**
             * 声明一个队列，
             *      参数1：队列名取值任意
             *      参数2：是否为持久化队列
             *      参数3：是否排外，如果排外则这个队列只允许一个消费者运行
             *      参数4：是否自动删除队列，如果为true则表示当队列中没有消息，也没有消费者指向这个队列时，自动删除队列
             *      参数5：队列的一些属性设置，一般为null即可
             * 注意：
             *      1、声明队列时，这个队列名称如果已经存在则放弃声明，如果队列不存在则会声明一个新的队列
             *      2、队列名可以任意取值，但是要与消息接受时指定的队列名完全一致
             *      3、这行代码可有可无，但是一定要在发送消息前确保队列名已经存在在RabbitMQ中，否则无法成功发送消息
             */
            channel.queueDeclare("myQueue", true, false, false, null);
            String massage = "HelloWorld!!";
            /**
             * 发送消息到MQ，
             * 参数1：交换机名称，空字符串表示不实用交换机
             * 参数2：队列名或RoutingKey，当指定了交换机名称后，这个值就是RoutingKey
             * 参数3：为消息属性信息，通常为null即可
             * 参数4：为具体的消息数据的字节数组
             *  注意：队列名必须要与接收时完全一致
             */
            channel.basicPublish("", "myQueue", null, massage.getBytes("utf-8"));
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
