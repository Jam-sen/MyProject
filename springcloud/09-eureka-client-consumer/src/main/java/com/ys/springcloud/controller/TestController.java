package com.ys.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;
    @RequestMapping("/test")
    public String test() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://08-EUREKA-CLIENT-PROVIDER/test", String.class);
        String result = forEntity.getBody();
        return "使用了Eureka的服务消费者-->"+result;
    }
}
