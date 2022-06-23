package com.ys.callable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = "com.ys.callable.dao")
@SpringBootApplication
public class CallableTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallableTestApplication.class, args);
	}

}
