package com.ys.springboot;

import com.ys.springboot.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		/**
		 * SpringBoot 程序启动后，返回值是 ConfigurableApplicationContext，它也是一个Spring 容器对象
		 * 它其它相当于原来Spring中启动容器：
		 *      ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("");
		 */
		//获取 SpringBoot 程序启动后的 Spring 容器
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		//从 Spring 容器中获取指定 bean 的对象
		StudentService studentService = (StudentService) applicationContext.getBean("studentServiceImpl");
		//执行业务bean的方法
		String s = studentService.sayHello();
		System.out.println(s);
	}

}
