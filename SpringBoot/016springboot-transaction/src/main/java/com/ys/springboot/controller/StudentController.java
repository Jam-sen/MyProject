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
    @RequestMapping("/update")
    @ResponseBody
    public String update(Student student) {
        int updateCount = studentService.updateById(student);
        return "修改学生编号为" + student.getId() + ",修改" + (updateCount == 1 ? "成功" : "失败");
    }
}
