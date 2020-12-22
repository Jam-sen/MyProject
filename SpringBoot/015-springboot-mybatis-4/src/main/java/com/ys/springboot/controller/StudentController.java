package com.ys.springboot.controller;

import com.ys.springboot.domain.Student;
import com.ys.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student")
    @ResponseBody
    public Student queryById(Integer id) {
        return studentService.queryById(id);
    }
}
