package com.ys.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication //开启spring的配置
@ServletComponentScan(basePackages = "com.ys.springboot.servlet") //扫描Servlet注解
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
