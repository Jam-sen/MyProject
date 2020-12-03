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
    private UserService userService1;

    @Autowired
    private UserService userService2;

    @RequestMapping(value = "/userDetail1")
    public String getUserById1(Model model, Integer id, String name) {
        User user1 = userService1.queryUserById(id, name);
        model.addAttribute("user1", user1);
        return "userDetail";
    }
    @RequestMapping(value = "/userDetail2")
    public String getUserById2(Model model, Integer id, String name) {
        User user2 = userService2.queryUserById(id, name);
        model.addAttribute("user2", user2);
        return "userDetail";
    }
}
