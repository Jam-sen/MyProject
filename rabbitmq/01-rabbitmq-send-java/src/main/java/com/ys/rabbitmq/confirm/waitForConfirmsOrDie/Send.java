package com.ys.rabbitmq.confirm.waitForConfirmsOrDie;

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
             * 批量消息确认，会同时向服务中确认当前通道中发送的所有消息是否全部成功发送
             * 这个方法没有返回值，如果服务器中有一条消息没有能够成功发送或向服务器发送确认时服务不可访问，都被认定为消息确认失败，此时方法会抛出InterruptedException异常，则需要补发消息到队列。
             * waitForConfirmsOrDie方法可以指定一个参数timeout，用于等待服务器的确认时间，如果超过这个时间也会抛出异常，表示确认失败需要补发消息。
             *      注意：
             *          批量消息确认的速度比普通的消息确认要快，但是如果一旦出现了消息补发的情况，我们不能确定具体是那条消息没有完成发送，需要将本次发送的所有消息全部进行补发。
             */
            channel.waitForConfirmsOrDie();
            System.out.println("消息发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            //在此处进行消息补发
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
