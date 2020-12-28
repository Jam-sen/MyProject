package com.ys.springboot;

import com.ys.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	//第二步:通过容器获取 bean，并注入给 userService
	@Autowired
	private StudentService studentService;

	public static void main(String[] args) {
		//第一步:SpringBoot 的启动程序，会初始化 spring 容器
		SpringApplication.run(Application.class, args);

	}

	//重写CommandLineRunner类中的run方法
	@Override
	public void run(String... args) throws Exception {
		//第三步:容器启动后自动调用 run 方法，在该方法中调用业务方法
		String sayHello = studentService.sayHello("World");
		System.out.println(sayHello);
	}
}
