package com.ys.springsession.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class TestController {
    @RequestMapping(value = "/set")
    @ResponseBody
    public Object set(HttpSession session) {
        session.setAttribute("data", "HelloWorld!");
        return "session设置成功";
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public Object get(HttpSession session) {
        return session.getAttribute("data");
    }
}
