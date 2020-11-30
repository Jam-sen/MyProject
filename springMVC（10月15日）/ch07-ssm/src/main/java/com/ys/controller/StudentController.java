package com.ys.controller;

import com.ys.domain.Student;
import com.ys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Qualifier("studentServiceImpl")
    @Autowired
    private StudentService service;
    @RequestMapping("/register.do")
    public ModelAndView Register(Student student) {
        int result = service.addStudent(student);
        ModelAndView modelAndView = new ModelAndView();
        if (result == 1) {
            modelAndView.addObject("result","添加学生"+student.getName()+"成功");
        }else{
            modelAndView.addObject("result","添加失败");
        }
        modelAndView.setViewName("result");
        return modelAndView;
    }
    @RequestMapping("/showStudents.do")
    @ResponseBody
    public List<Student> showStudents() {
        return service.findStudents();
    }
}
