package com.ys.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ys.springboot.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    @Reference(interfaceClass = StudentService.class,version = "1.0.0")
    private StudentService studentService;
    @RequestMapping(value = "/student/count")
    @ResponseBody
    public String StudentCount() {
        Integer count = studentService.queryAllStudentCount();
        return "学生总人数为：" + count;
    }
}
