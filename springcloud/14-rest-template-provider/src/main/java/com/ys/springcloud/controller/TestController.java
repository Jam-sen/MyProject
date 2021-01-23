package com.ys.springcloud.controller;

import com.ys.springcloud.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TestController {
    @RequestMapping(value = "/getForEntity01")
    public Object getForEntity01() {
        User user = new User();
        user.setAge(21);
        user.setId(1L);
        user.setName("zhangsan");
        //Spring会将User对象转换成对应的Json数据返回
        return user;
    }

    @RequestMapping(value = "/getForEntity02")
    public Object getForEntity02() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(2L, "wangwu", 23));
        userList.add(new User(2L, "wangwu2", 23));
        userList.add(new User(2L, "wangwu3", 23));
        //Spring会将User对象转换成对应的Json数据返回
        return userList;
    }

    @RequestMapping(value = "/getForEntityParams01")
    public Object getForEntityParam01(User user) {
        user.setName(user.getName() + "服务提供者");
        return user;
    }

    @RequestMapping(value = "/getForEntityParams02")
    public Object getForEntityParam02(User user) {
        user.setName(user.getName() + "服务提供者");
        return user;
    }

    @RequestMapping(value = "/getForObject")
    public Object getForObject() {
        return new User(4L, "赵六", 25);
    }

    @PostMapping(value = "/postForObject")
    public Object postForObject(User user) {
        user.setName(user.getName() + "->服务提供者");
        return user;
    }

    @PutMapping("/put")
    public void put() {
        System.out.println("服务提供者被调用");
    }

    @DeleteMapping("/delete")
    public void delete(Long id) {
        System.out.println(id+"号删除成功");
    }
}
