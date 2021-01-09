package com.ys.rabbitmq.transaction;

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
            channel.queueDeclare("transactionQueue", true, false, false, null);

            channel.exchangeDeclare("directTransactionExchange", "direct", true);
            channel.queueBind("transactionQueue", "directTransactionExchange", "directTransactionRoutingKey");

            String message = "事务的测试消息";
            channel.txSelect();//开启事务

            channel.basicPublish("directTransactionExchange", "directTransactionRoutingKey", null, message.getBytes("utf-8"));
            channel.basicPublish("directTransactionExchange", "directTransactionRoutingKey", null, message.getBytes("utf-8"));

            channel.txCommit();//提交事务，提交后会将内存中的消息写入队列并释放内存
            System.out.println("消息发送成功");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }finally {
            if (channel != null) {
                try {
                    channel.txRollback();//在channel关闭之前，回滚事务，放弃当前事务中所有没有提交的消息，释放内存
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
