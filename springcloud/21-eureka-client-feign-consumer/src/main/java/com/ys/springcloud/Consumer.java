package com.ys.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//激活声明式服务消费Feign的支持
@EnableFeignClients
public class Consumer {

	public static void main(String[] args) {
		SpringApplication.run(Consumer.class, args);
	}

}
