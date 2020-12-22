package com.ys.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @RequestMapping(value = "/condition")
    public ModelAndView condition() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sex", 1);
        modelAndView.addObject("flag", true);
        modelAndView.addObject("productType", 0);
        modelAndView.setViewName("condition");
        return modelAndView;
    }
}
