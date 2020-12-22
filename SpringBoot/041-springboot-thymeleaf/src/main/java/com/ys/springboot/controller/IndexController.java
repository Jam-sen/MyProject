package com.ys.springboot.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("data", "springboot thymeleaf");
        return "index";
    }
}
