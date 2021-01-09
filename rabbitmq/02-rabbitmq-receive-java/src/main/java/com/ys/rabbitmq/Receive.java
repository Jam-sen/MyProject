package com.ys.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUsername("root");
        connectionFactory.setPort(5672);
        connectionFactory.setPassword("yaosen..");
        connectionFactory.setHost("localhost");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            /**
             * 接收消息，
             * 参数1：当前消费者需要监听的队列名，队列名必须要与发送时的队列名一致，否则接受不到消息
             * 参数2：消息是否自动确认，true表示自动确认，自动确认在接收完消息后会自动将消息从队列中移除
             * 参数3：消息接收者的标签，用于当多个消费者同时监听一个队列时，区分不同消费者，通常为空字符串即可
             * 参数4：接收消息后的回调方法，在这个方法中具体完成消息的处理代码
             *  注意：使用了basicConsume方法以后，会启用一个线程在持续的监听队列，如果队列中有消息进入则会自动接收消息，因此不能关闭连接和通道对象
             */
            channel.basicConsume("myQueue", true, "", new DefaultConsumer(channel){
                //消息的具体接收和处理方法
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "utf-8");
                    System.out.println("接收到的消息-->"+message);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
