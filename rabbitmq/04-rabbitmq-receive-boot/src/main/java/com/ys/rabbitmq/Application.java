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
		//使用了消息监听器接收消息，就不需要调用接收方法来接收消息
		//myService.receive();
	}

}
