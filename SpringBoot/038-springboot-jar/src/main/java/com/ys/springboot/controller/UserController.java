package com.ys.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @RequestMapping(value = "/user/json/detail")
    @ResponseBody
    public Object userJsonDetail() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1001);
        map.put("name", "王五");
        return map;
    }

    @RequestMapping(value = "/user/page/detail")
    public ModelAndView userPageDetail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userDetail");
        modelAndView.addObject("id", 1001);
        modelAndView.addObject("name", "王五");
        return modelAndView;
    }
}
