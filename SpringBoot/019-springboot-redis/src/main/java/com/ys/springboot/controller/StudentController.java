package com.ys.springboot.controller;

import com.ys.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @RequestMapping(value = "/put")
    public String put(String key, String value) {
        studentService.put(key, value);
        return "值已成功放入redis";
    }

    @RequestMapping(value = "/get")
    public String get(String key) {
        return studentService.get(key) + "---" +redisTemplate.keys("*");
    }
}