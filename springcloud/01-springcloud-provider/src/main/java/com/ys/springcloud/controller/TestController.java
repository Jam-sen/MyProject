package com.ys.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作为springcloud的服务提供者，控制器要求返回一个Rest风格的数据，因此我们这里直接使用@RestController注解，返回一个json数据
 */
@RestController
public class TestController {
    @RequestMapping(value = "/test")
    public String test() {
        return "第一个springcloud提供者";
    }
}
