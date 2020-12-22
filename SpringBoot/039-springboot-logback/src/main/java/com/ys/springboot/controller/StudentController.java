package com.ys.springboot.controller;

import com.ys.springboot.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/count")
    @ResponseBody
    public String studentCount() {
        log.trace("查询学生总人数");
        log.debug("查询学生总人数");
        log.info("查询学生总人数");
        log.warn("查询学生总人数");
        log.error("查询学生总人数");
        Integer count = studentService.getStudentsCount();
        return "学生总人数为：" + count;
    }
}
