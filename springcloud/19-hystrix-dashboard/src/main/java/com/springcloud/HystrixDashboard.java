package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//激活Hystrix的仪表盘功能
@EnableHystrixDashboard
public class HystrixDashboard {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboard.class, args);
	}

}
