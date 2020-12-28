package com.ys.springboot.controller;

import com.ys.springboot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class StudentController {
    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        User user = new User();
        user.setAge(21);
        user.setId(1001);
        user.setName("张三");
        request.getSession().setAttribute("user", user);
        return "login success";
    }

    //需要登录后才能访问
    @RequestMapping(value = "/center")
    @ResponseBody
    public String center() {
        return "center massage";
    }

    //无需登录就能访问的资源
    @RequestMapping(value = "/out")
    @ResponseBody
    public String out() {
        return "out massage";
    }

    //如果用户未登录，却访问了需要登录才能访问的资源，则跳转至error
    @RequestMapping(value = "/error")
    @ResponseBody
    public String error() {
        return "error,please login";
    }
}
