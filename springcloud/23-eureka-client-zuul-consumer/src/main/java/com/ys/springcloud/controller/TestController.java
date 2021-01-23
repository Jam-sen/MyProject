package com.ys.springcloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/test")
    public String test() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://24-eureka-client-zuul-provider/test", String.class);
        String body = forEntity.getBody();
        return "没有经过网关zuul的服务消费者-->" + body;
    }

    @RequestMapping("/test02")
    public String test02(String token) {
        System.out.println(token);
        String url = "http://26-EUREKA-CLIENT-ZUUL-GATEWAY/myapi/api-zuul/test?token={token}";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class,token);
        String body = forEntity.getBody();
        return "经过了网关zuul的服务消费者-->" + body;
    }
}
