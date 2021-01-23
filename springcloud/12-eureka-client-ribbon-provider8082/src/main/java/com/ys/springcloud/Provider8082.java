package com.ys.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Provider8082 {

	public static void main(String[] args) {
		SpringApplication.run(Provider8082.class, args);
	}

}
