package com.ys.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("sex", 1);
        model.addAttribute("data", "springboot data");
        model.addAttribute("flag", true);
        return "literal";
    }

    @RequestMapping("/splice")
    public String splice(Model model) {
        model.addAttribute("totalRows", 123);
        model.addAttribute("totalPage", 13);
        model.addAttribute("currentPage", 2);
        return "splice";
    }
}
