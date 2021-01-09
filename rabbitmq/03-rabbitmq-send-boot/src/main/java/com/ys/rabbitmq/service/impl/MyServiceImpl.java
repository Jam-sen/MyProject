package com.ys.rabbitmq.service.impl;

import com.ys.rabbitmq.service.MyService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {
    //注入Amqp的模版类，利用这个对象发送和接收消息
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String message) {
        /**
         * 发送消息
         *  参数1：交换机名
         *  参数2：RoutingKey
         *  参数3：具体发送的消息数据
         */
        amqpTemplate.convertAndSend("bootDirectExchange","bootDirectRoutingKey",message);
    }

    @Override
    public void sendFanoutMessage(String message) {
        amqpTemplate.convertAndSend("fanoutExchange","",message);
    }

    @Override
    public void sendTopicMessage(String message) {
        amqpTemplate.convertAndSend("topicExchange", "aa.bb.cc", message);
    }


}
