package com.ys.springcloud.controller;

import com.ys.springcloud.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        System.out.println(10/0);
        return "使用了Feign的服务提供者";
    }


    @RequestMapping("/test01")
    public String test01(String name, Integer age) {
        return "使用了Feign的服务提供者" + name + " " + age;
    }

    /**
     * 使用User对象接收数据
     */
    @RequestMapping("/test02")
    public String test02(@RequestBody User user) {
        return "使用了Feign的服务提供者" + user.getName() + "->" + user.getAge();
    }

    /**
     * 如果服务提供者返回的数据符合Json对象格式那么我们就可以使用一个实体类或Map接收响应数据
     */
    @RequestMapping("/testReturnUser03")
    User testReturnUser() {
        return new User("克里斯", 24);
    }

    @RequestMapping("testReturnList04")
    public List<User> testReturnList04() {
        User user1 = new User("zhangsan", 14);
        User user2 = new User("lisi", 17);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return users;
    }
}