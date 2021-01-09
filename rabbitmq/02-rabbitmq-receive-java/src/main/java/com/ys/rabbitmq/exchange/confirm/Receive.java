package com.ys.rabbitmq.exchange.confirm;

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
            channel.queueDeclare("confirmQueue", true, false, false, null);//声明队列
            channel.exchangeDeclare("directConfirmExchange", "direct", true);//声明交换机
            channel.queueBind("confirmQueue", "directConfirmExchange", "confirmRoutingKey");//队列绑定到交换机

            //启动事务
            channel.txSelect();

            /**
             * 接收消息，
             * 参数1：指定队列名称
             * 参数2：true表示自动确认接收，确认后消息会从队列中被移除；false表示手动确认
             *  注意：
             *      1、如果我们只是确认接收了消息但是还没有来得及处理，当前应用就崩溃了或在处理过程中程序出现异常（例如向数据库写入数据但数据库这时不可用），那么由于消息是自动确认的，导致这部分消息就会在接收完成以后自动从队列中被删除，这就会丢失消息。
             *      2、将自动确认接收消息关闭，可以防止因未来及处理消息导致的消息丢失。
             */
            channel.basicConsume("confirmQueue", false, new DefaultConsumer(channel) {
                /**
                 *回调方法：消息的具体接收和处理，监听某个队列并获取队列中的数据，
                 *  注意：当前被监听的队列必须已经存在并正确的绑定在某个交换机上
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    /**
                     * 获取当前消息是否被接收过一次，如果返回false表示消息之前没有被接收过，返回true则表示这个消息之前被接收过，可能也处理完成。因此我们要进行消息的防重复处理。
                     *
                     */
                    boolean isRedeliver = envelope.isRedeliver();
                    System.out.println("当前消息是否被接收过一次：" + isRedeliver);
                    //获取当前内部类中的通道channel
                    Channel c = this.getChannel();
                    //获取消息的编号，我们需要根据消息的编号来确认消息
                    long deliveryTag = envelope.getDeliveryTag();
                    if (!isRedeliver) {
                        String message = new String(body, "utf-8");
                        System.out.println("接收到的消息-->" + message);
                        /**
                         * 手动确认消息，确认以后表示当前消息已经成功处理了，需要从队列中移除掉，这个方法应该在当前消息的处理程序全部完成以后执行
                         *  参数1：为消息的序列号
                         *  参数2：是否确认多个，如果为true则表示需要确认小于等于当前编号的所有消息，false就是单个确认，只确认当前消息
                         */
                        c.basicAck(deliveryTag, true);
                        //注意：如果启动了事务，而消息消费者确认模式为手动确认那么必须要提交事务，否则即使调用basicAck（）方法，消息也不会从队列中被移除掉
                    } else {
                        /**
                         * 程序到了这里表示这个消息之前已经被接收过，需要进行防重复处理
                         * 例如：查询数据库中是否已经添加了记录或修改了记录。
                         *      如果经过查询判断，这条消息没有被处理完成，则需要重新处理，然后确认掉这条消息。
                         *      如果经过判断，这是已经处理过的消息，则直接确认消息即可，不需要进行其他处理操作。
                         */
                        c.basicAck(deliveryTag, true);
                    }
                    c.txCommit();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
