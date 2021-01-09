package com.ys.rabbitmq.exchange.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Receive01 {
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

            /**
             * 由于Fanout类型的交换机的消息时类似于广播的模式，它不需要绑定RoutingKey
             * 而又可能会有很多个消费者来接收这个交换机中的数据，因此我们创建队列时要创建一个随机的队列名称
             *
             * 没有参数的queueDeclare方法会创建一个名字为随机的一个队列
             *  这个队列的数据是非持久化
             *  是排外的（同时最多只允许有一个消费者监听当前队列）
             *  自动删除的 当没有任何消费者监听队列时这个队列会自动删除
             *
             * getQueue() 方法用于获取这个随机的队列名
             */
            String queueName = channel.queueDeclare().getQueue();//声明随机队列,  并取得队列名
            channel.exchangeDeclare("fanoutExchange", "fanout", true);//声明交换机
            //将这个随机的队列绑定到交换机中，由于是fanout类型的交换机因此不需指定RoutingKey进行绑定
            channel.queueBind(queueName, "fanoutExchange", "");
            /**
             * 接收消息，
             * 参数1：指定队列名称
             * 参数2：自动确认接收
             */
            channel.basicConsume(queueName,true,new DefaultConsumer(channel) {
                /**
                 *回调方法：消息的具体接收和处理，监听某个队列并获取队列中的数据，
                 *  注意：当前被监听的队列必须已经存在并正确的绑定在某个交换机上
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "utf-8");
                    System.out.println("01接收到的消息-->"+message);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
