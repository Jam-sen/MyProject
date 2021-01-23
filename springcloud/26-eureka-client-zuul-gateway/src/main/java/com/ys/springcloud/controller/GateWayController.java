package com.ys.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateWayController {
    @RequestMapping("/api/local")
    public String test() {
        System.out.println("123123weqw123123qwe");
        return "网关工程自己的控制器方法";
    }
}
