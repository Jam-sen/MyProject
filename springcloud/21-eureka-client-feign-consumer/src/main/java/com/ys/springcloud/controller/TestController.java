package com.ys.springcloud.controller;

import com.ys.springcloud.model.User;
import com.ys.springcloud.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {
    //这里直接注入Spring动态代理生成的TestService对象
    @Resource
    private TestService testService;

    @RequestMapping("/test")
    public String test() {
        String result = testService.test();
        return "使用了Feign的服务消费者--" + result;
    }

    @RequestMapping("/test01")
    public String test01() {
        String result = testService.test01("张三", 23);
        return "使用了Feign的服务消费者--" + result;
    }

    @RequestMapping("/test02")
    public String test02() {
        User user = new User("克里斯", 24);
        String result = testService.test02(user);
        return "使用了Feign的服务消费者--" + result;
    }

    @RequestMapping("testReturnUser03")
    public User testReturnUser03() {
        return testService.testReturnUser03();
    }

    @RequestMapping("testReturnList04")
    public List<User> testReturnList04() {
        return testService.testReturnList04();
    }
}
