package com.ys.springboot.controller;

import com.ys.springboot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/detail")
    public ModelAndView userDetail() {
        User user = new User();
        user.setName("zhangsan");
        user.setId(1001);
        user.setAge(20);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userDetail");
        return modelAndView;
    }

    @RequestMapping(value = "/url")
    public ModelAndView urlExpression() {
        User user = new User();
        user.setName("wangwu");
        user.setId(1001);
        user.setAge(20);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("url");
        return modelAndView;
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(String name) {
        return "请求路径/user/test，参数是：" + name;
    }

    @RequestMapping(value = "/test1")
    @ResponseBody
    public String test1(String name, Integer id, Integer age) {
        return "请求路径/user/test1，参数是：id=" + id + "，name=" + name + "，age=" + age;
    }

    @RequestMapping(value = "/test2/{id}/{name}/{age}")
    @ResponseBody
    public String test2(@PathVariable String name, @PathVariable Integer id, @PathVariable Integer age) {
        return "请求路径/user/test1，参数是：id=" + id + "，name=" + name + "，age=" + age;
    }

    @RequestMapping(value = "/property")
    public String property() {
        return "property";
    }
}
