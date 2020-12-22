package com.ys.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @RequestMapping(value = "/message")
    public ModelAndView message() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data", "SpringBoot集成Thymeleaf模版引擎");
        modelAndView.setViewName("message");
        return modelAndView;
    }
}
