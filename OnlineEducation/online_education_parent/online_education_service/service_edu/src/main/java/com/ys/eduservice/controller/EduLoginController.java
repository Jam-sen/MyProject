package com.ys.eduservice.controller;

import com.ys.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    @PostMapping("/login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("/info")
    public R info() {
        return R.ok().data("roles", "admin").data("name", "admin").data("avatar", "https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/wh%3d450%2c600/sign=3a3b7df7e3fe9925cb596154019872e9/6159252dd42a28343489533958b5c9ea15cebfd0.jpg");
    }
}
