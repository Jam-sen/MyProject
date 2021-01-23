package com.ys.rabbitmq.service.impl;

import com.ys.rabbitmq.service.MyService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {
    //注入Amqp的模版类，利用这个对象发送和接收消息
    @Autowired
    private AmqpTemplate amqpTemplate;

    //这个接收不是不间断的接收消息，每执行一次这个方法只能接收一次消息，如果有新消息进入则不会自动接收消息，不建议使用这种方法
    @Override
    public void receive() {
        //参数String指定要接收消息的队列名
        String message = (String) amqpTemplate.receiveAndConvert("bootDirectQueue");
        System.out.println(message);
    }


    /**
     * @param message 接收到的具体的消息数据
     * @RabbitListener 注解用于标记当前方法是一个RabbitMQ的消息监听方法，作用是持续性的接收消息。这个方法不需要手动调用，springboot会自动运行这个监听。
     * 属性：
     *  queues 用于指定一个已经存在的队列名，用于进行队列的监听
     *
     *  注意：如果当前监听方法正常结束Spring就会自动确认消息；
     *       如果出现异常则不会确认消息,导致监听器反复接收消息，因此在消息处理时我们需要做好消息的防止重复处理工作
     */
    @RabbitListener(queues = {"bootDirectQueue"})
    public void directReceive(String message) {
        System.out.println("监听器接收到的消息-->"+ message);
    }

    //@QueueBinding注解要完成队列和交换机的绑定；@Queue注解创建一个队列（没有指定参数则表示创建随机队列）；@Exchange注解创建交换机
    @RabbitListener(bindings = {@QueueBinding(value = @Queue,exchange = @Exchange(name = "fanoutExchange",type = "fanout"))})
    public void fanoutReceive1(String message) {
        System.out.println("fanoutReceive1监听器接收到的消息-->"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue,exchange = @Exchange(name = "fanoutExchange",type = "fanout"))})
    public void fanoutReceive2(String message) {
        System.out.println("fanoutReceive2监听器接收到的消息-->"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(name = "topic01"),key = {"aa"},exchange = @Exchange(name = "topicExchange",type = "topic"))})
    public void topicReceive1(String message) {
        System.out.println("topicReceive1（aa）接收到消息-->"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(name = "topic02"),key = {"aa.*"},exchange = @Exchange(name = "topicExchange",type = "topic"))})
    public void topicReceive2(String message) {
        System.out.println("topicReceive1（aa.*）接收到消息-->"+message);
    }

    @RabbitListener(bindings = {@QueueBinding(value = @Queue(name = "topic03"),key = {"aa.#"},exchange = @Exchange(name = "topicExchange",type = "topic"))})
    public void topicReceive3(String message) {
        System.out.println("topicReceive1（aa.#）接收到消息-->"+message);
    }


}
