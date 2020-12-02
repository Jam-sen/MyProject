package com.ys.controller;

import com.ys.domain.User;
import com.ys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/userDetail")
    public String userDetail(Model model, Integer id) {
        //根据用户id获取用户详情
        User user = userService.queryById(id);
        //获取用户总人数
        Integer count = userService.queryAllUserCount();
        model.addAttribute("user", user);
        model.addAttribute("count", count);
        return "userDetail";
    }
}
