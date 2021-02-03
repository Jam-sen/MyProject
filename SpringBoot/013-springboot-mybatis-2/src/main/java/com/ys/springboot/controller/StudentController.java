package com.ys.springboot.controller;

import com.ys.springboot.dao.StudentMapper;
import com.ys.springboot.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class StudentController {
    @Resource
    private StudentMapper studentMapper;

    @RequestMapping(value = "/getDetail")
    @ResponseBody
    public Student getDetailById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
