package com.ys.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/springboot")
public class IndexController {

    @RequestMapping(value = "/say")
    @ResponseBody
    public String say(String massage) {
        return "say:Hello SpringBoot"+massage;
    }

    @RequestMapping(value = "/getMap")
    public @ResponseBody Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "SpringBoot Project");
        return map;
    }

}
