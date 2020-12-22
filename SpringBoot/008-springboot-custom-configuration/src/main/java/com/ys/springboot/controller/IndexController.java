package com.ys.springboot.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping(value = "/springboot")
public class IndexController {
    @Value("${school.name}")
    private String schoolName;

    @Value("${school.websit}")
    private String websit;

    @RequestMapping(value = "/say")
    @ResponseBody
    public String say() throws IOException {
        return "helloSpringBoot " + schoolName + " " + websit;
    }
}
