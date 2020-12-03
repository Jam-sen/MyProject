package com.ys.controller;

import com.ys.domain.User;
import com.ys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userDetail")
    public String userDetail(Model model, Integer id, String name) {
        User user = userService.queryUserById(id, name);
        model.addAttribute("user", user);
        return "userDetail";
    }
}
