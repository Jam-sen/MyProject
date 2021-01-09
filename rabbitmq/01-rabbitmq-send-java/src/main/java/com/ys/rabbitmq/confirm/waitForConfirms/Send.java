package com.ys.rabbitmq.confirm.waitForConfirms;

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
            channel.queueDeclare("confirmQueue", true, false, false, null);

            channel.exchangeDeclare("directConfirmExchange", "direct", true);
            channel.queueBind("confirmQueue", "directConfirmExchange", "directConfirmRoutingKey");

            String message = "普通发送者确认模式的测试消息";
            //启动发送者确认模式
            channel.confirmSelect();

            channel.basicPublish("directConfirmExchange", "directConfirmRoutingKey", null, message.getBytes("utf-8"));
            channel.basicPublish("directConfirmExchange", "directConfirmRoutingKey", null, message.getBytes("utf-8"));

            /**
             * 阻塞线程等待服务返回响应，用于确认消息是否发送成功，如果服务确认消息已经发送完成，则返回true，否则返回false
             * 可以为这个方法指定一个毫秒值，指定等待服务确认的超时时长
             * 如果超过了指定的时间以后则会抛出异常InterruptedException 表示服务器出现问题了，需要补发消息，或将消息缓存到Redis中稍后利用定时任务补发
             * 无论是返回false还是抛出异常消息，都有可能发送成功，也有可能发送失败
             * 如果我们要求这个消息一定要发送到队列，例如订单数据，那么我们可以采用消息补发，可以使用递归或Redis+定时任务来完成补发
             */
            boolean flag = channel.waitForConfirms();
            System.out.println("消息发送成功" + flag);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
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
