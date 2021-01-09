package com.ys.rabbitmq.exchange.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive03 {
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

            channel.queueDeclare("topicQueue03",true,false,false,null);
            channel.exchangeDeclare("topicExchange", "topic", true);//声明交换机
            channel.queueBind("topicQueue03", "topicExchange", "aa.#");

            channel.basicConsume("topicQueue03",true,new DefaultConsumer(channel) {
                /**
                 *回调方法：消息的具体接收和处理，监听某个队列并获取队列中的数据，
                 *  注意：当前被监听的队列必须已经存在并正确的绑定在某个交换机上
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "utf-8");
                    System.out.println("03接收到的消息aa.#-->"+message);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
