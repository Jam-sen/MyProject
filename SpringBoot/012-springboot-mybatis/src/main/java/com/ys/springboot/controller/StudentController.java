package com.ys.springboot.controller;

import com.ys.springboot.domain.Student;
import com.ys.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/getDetail")
    @ResponseBody
    public Student getDetailById(Integer id) {
        return studentService.getDetailById(id);
    }
}
