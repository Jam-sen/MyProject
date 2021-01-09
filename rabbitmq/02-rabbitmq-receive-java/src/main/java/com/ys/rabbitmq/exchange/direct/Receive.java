package com.ys.rabbitmq.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive {
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

            //这三行代码可有可无，但是要确保在接收消息时，队列与交换机都存在并正确绑定。
            //在接收端也声明，可以先启动发布端，也可以先启动接收端
            channel.queueDeclare("myDirectQueue", true, false, false, null);//声明队列
            channel.exchangeDeclare("directExchange", "direct", true);//声明交换机
            channel.queueBind("myDirectQueue", "directExchange", "directRoutingKey");//队列绑定到交换机
            /**
             * 接收消息，
             * 参数1：指定队列名称
             * 参数2：自动确认接收
             */
            channel.basicConsume("myDirectQueue",true,new DefaultConsumer(channel) {
                /**
                 *回调方法：消息的具体接收和处理，监听某个队列并获取队列中的数据，
                 *  注意：当前被监听的队列必须已经存在并正确的绑定在某个交换机上
                 */
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
