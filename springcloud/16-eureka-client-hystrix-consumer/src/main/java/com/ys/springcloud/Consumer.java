package com.ys.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@SpringBootApplication
//@EnableEurekaClient
////激活Hystrix的熔断机制
//@EnableCircuitBreaker
@SpringCloudApplication
public class Consumer {

	public static void main(String[] args) {
		SpringApplication.run(Consumer.class, args);
	}

}
