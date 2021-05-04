package com.ys.springcloud.controller;

import com.ys.springcloud.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping("/test")
    public String test() {
        return "带有熔断机制的服务提供者";
    }

    @RequestMapping("/test02")
    public String test02() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "带有熔断机制的服务提供者延迟操作";
    }

    @RequestMapping("/test03")
    public String test03() {
        System.out.println(10 / 0);
        return "带有熔断机制的服务提供者";
    }

    @RequestMapping("/test05")
    public String test05() {
        System.out.println(10 / 0);
        return "带有熔断机制的服务提供者";
    }

    @RequestMapping("/test06")
    public String test06() {
        return "服务提供者test06执行";
    }
}
