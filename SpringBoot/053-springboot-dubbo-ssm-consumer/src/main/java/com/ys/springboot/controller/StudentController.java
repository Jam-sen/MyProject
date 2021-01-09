package com.ys.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ys.springboot.domain.Student;
import com.ys.springboot.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentController {
    @Reference(interfaceClass = StudentService.class, version = "1.0.0")
    private StudentService studentService;

    @RequestMapping(value = "/student/detail/{id}")
    public String studentDetail(Model model, @PathVariable Integer id) {
        Student student = studentService.queryStudentById(id);
        model.addAttribute("student", student);
        return "studentDetail";
    }

    @RequestMapping(value = "/login")
    public String login(String id, String password,Model model, HttpServletRequest request) {
        if ("123".equals(password)) {
            request.getSession().setAttribute("登录状态","已登录");
            model.addAttribute("id", id);
            return "workpage";
        }
        return "index";
    }
}
