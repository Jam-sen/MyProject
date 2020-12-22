package com.ys.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ys.springboot.dao")//开启扫描dao包及子包下的Mapper接口
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
