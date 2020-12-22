package com.ys.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/springboot")
public class IndexController {

    @RequestMapping(value = "/say")
    public String say(Model model) {
        model.addAttribute("massage", "HelloSpringBoot");
        return "index";
    }
    @RequestMapping(value = "/index")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("massage", "HelloAlibaba");
        return "index";
    }
}
