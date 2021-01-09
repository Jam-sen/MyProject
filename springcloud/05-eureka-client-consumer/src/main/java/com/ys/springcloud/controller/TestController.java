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
        /**
         * 通过注册中心发现服务并访问服务，其中04-eureka-client-provider就是服务在注册中心的名称（不区分大小写）
         * springcloud会根据这个服务名，到注册中心中获取服务名对应的服务所在地IP和端口号，然后利用RestTemplate访问服务。
         */
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://04-eureka-client-provider/test", String.class);
        String body = forEntity.getBody();
        return "使用了Eureka的服务消费者-->" + body;
    }

    @RequestMapping("/dosome")
    public String dosome() {
        return "dosome";
    }
}
