package com.ys.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ys.springboot.domain.Student;
import com.ys.springboot.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    @Reference(interfaceClass = StudentService.class,version = "1.0.0")
    private StudentService studentService;

    @RequestMapping(value = "/detail/{id}")
    public ModelAndView studentDetail(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Student student = studentService.getStudentById(id);
        modelAndView.addObject("student", student);
        modelAndView.setViewName("detail");
        return modelAndView;
    }

    @RequestMapping(value = "/count")
    public ModelAndView studentsCount() {
        ModelAndView modelAndView = new ModelAndView();
        Integer count = studentService.getStudentsCount();
        modelAndView.addObject("count", count);
        modelAndView.setViewName("detail");
        return modelAndView;
    }
}
