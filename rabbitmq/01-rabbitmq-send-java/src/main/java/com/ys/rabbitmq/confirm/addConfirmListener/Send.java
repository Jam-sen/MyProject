package com.ys.rabbitmq.confirm.addConfirmListener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
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

            /**
             * 异步消息确认监听器，需要在发送消息前启动
             */
            channel.addConfirmListener(new ConfirmListener() {
                /**
                 * 消息确认以后的回调方法
                 * 参数1：被确认的消息的编号，从1开始自动递增，用于标记当前是第几个消息
                 * 参数2：当前消息是否同时确认了多个
                 *      注意：如果参数2为true，则表示本次同时确认了多条消息，确认消息数量等于当前参数1（消息编号）的值。
                 *           如果参数2为false，则表示只确认了当前编号的消息
                 */
                @Override
                public void handleAck(long l, boolean b) throws IOException {
                    System.out.println("消息被确认了--消息编号：" + l + ",是否确认多条消息：" + b);
                }

                /**
                 * 消息未被确认的回调方法，如果这个方法被执行表示当前消息没有被确认，需要消息补发
                 *  参数1：没有被确认的消息的编号，从1开始自动递增，用于标记当前是第几个消息
                 *  参数2：当前消息是否同时未被确认多个
                 *      注意：如果参数2为true，则表示小于当前编号的所有消息可能都没有发送成功，需要进行消息的补发
                 *           如果参数2为false，则表示当前编号的消息没发送成功，需要进行补发
                 */
                @Override
                public void handleNack(long l, boolean b) throws IOException {
                    System.out.println("消息未被确认--消息编号：" + l + ", 是否多条消息未被确认：" + b);
                }
            });
            for (int i = 0; i <= 1000; i++) {
                channel.basicPublish("directConfirmExchange", "directConfirmRoutingKey", null, message.getBytes("utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }  finally {
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
