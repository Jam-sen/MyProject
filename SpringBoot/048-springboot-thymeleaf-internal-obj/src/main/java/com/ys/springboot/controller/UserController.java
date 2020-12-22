package com.ys.springboot.controller;

import com.ys.springboot.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @RequestMapping(value = "/expression")
    public String expression(Model model, HttpServletRequest request) {
        model.addAttribute("username", "lisi");
        User user = new User();
        user.setId(1001);
        user.setUserAddress("甘肃省兰州市");
        user.setUserAge("21");
        user.setUserPhone("1398888888");
        request.getSession().setAttribute("user",user);
        request.getSession().setAttribute("address","甘肃省兰州市");
        return "index";
    }
}
