package com.ys.springboot.controller;

import com.ys.springboot.domain.Student;
import com.ys.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/update")
    public String update(Student student) {
        int updateCount = studentService.updateById(student);
        return "修改学生编号为" + student.getId() + ",修改" + (updateCount == 1 ? "成功" : "失败");
    }

    //    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @GetMapping(value = "/get")//该注解通常在查询数据时使用
    public String get() {
        return "Only GET Method";
    }

    //    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @PostMapping(value = "/isnert")//该注解通常在添加数据时使用
    public String Insert() {
        return "insert success";
    }

    //    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @DeleteMapping(value = "/delete")//该注解通常在删除数据时使用
    public String delete() {
        return "delete success";
    }

//    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @PutMapping(value = "/put")//该注解通常在更新数据的时候使用
    public String update() {
        return "update success";
    }
}
