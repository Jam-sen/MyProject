package com.ys.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class UserController {
    @RequestMapping(value = "/function")
    public String function(Model model) {
        model.addAttribute("time", new Date());
        model.addAttribute("data","SpringBootHelloWorld");
        return "function";
    }
}
