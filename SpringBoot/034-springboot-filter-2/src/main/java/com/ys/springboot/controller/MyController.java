package com.ys.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @RequestMapping(value = "/user/get")
    @ResponseBody
    public String get() {
        return "get success";
    }

    @RequestMapping(value = "/center")
    @ResponseBody
    public String center() {
        return "center data";
    }
}
