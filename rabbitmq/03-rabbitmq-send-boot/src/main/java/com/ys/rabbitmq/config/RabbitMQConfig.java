package com.ys.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //配置一个direct类型的交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("bootDirectExchange");
    }

    //配置一个队列
    @Bean
    public Queue directQueue() {
        return new Queue("bootDirectQueue");
    }

    /**
     * 配置一个队列和交换机的绑定
     * @param directQueue 需要绑定的队列的对象，参数名必须要与某个@Bean的方法名完全相同，这样就会自动进行注入.
     * @param directExchange 需要绑定的交换机的对象，参数名必须要与某个@Bean的方法名完全相同，这样就会自动进行注入.
     * @return
     */
    @Bean
    public Binding queueBinding(Queue directQueue, DirectExchange directExchange) {
        //完成绑定
        return BindingBuilder.bind(directQueue).to(directExchange).with("bootDirectRoutingKey");
    }

    //配置一个Fanout类型的交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    //配置一个Topic类型的交换机
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }
}
