package com.ys.springboot.controller;

import com.ys.springboot.config.Abc;
import com.ys.springboot.config.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(value = "/springboot")
public class IndexController {

    @Autowired
    private School school;

    @Autowired
    private Abc abc;

    @RequestMapping(value = "/say")
    @ResponseBody
    public String say() throws IOException {
        return "helloSpringBoot    " + school.getName() + "  " + school.getWebsit()+"  "+abc.getName()+"  "+abc.getWebsit();
    }
}
