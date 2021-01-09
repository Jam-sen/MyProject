package com.ys.rabbitmq;

import com.ys.rabbitmq.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		MyService myService = (MyService) applicationContext.getBean("myServiceImpl");
		//myService.sendMessage("springboot整合RabbitMQ-->测试数据");
		//myService.sendFanoutMessage("springboot整合RabbitMQ-->测试数据");
		myService.sendTopicMessage("springboot整合RabbitMQ-->测试数据");
	}

}
