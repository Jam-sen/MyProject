package com.ys.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/operator")
    public String literal(Model model) {
        model.addAttribute("sex", 1);
        model.addAttribute("data", "springboot data");
        model.addAttribute("flag", true);
        return "operator";
    }

}
