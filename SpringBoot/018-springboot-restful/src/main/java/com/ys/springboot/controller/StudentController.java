package com.ys.springboot.controller;

import com.ys.springboot.domain.Student;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @RequestMapping(value = "/student")
    public Student student(Integer id, Integer age) {
        Student student = new Student();
        student.setAge(age);
        student.setId(id);
        return student;
    }

    @GetMapping(value = "/student/{id}")
    public Student get(@PathVariable Integer id) {
        Student student = new Student();
        student.setId(id);
        return student;
    }

    @PostMapping(value = "/student/{id}/{name}/{age}")
    public Student insert(Student student) {
        return student;
    }

    @DeleteMapping(value = "/student/{id}")
    public String delete(@PathVariable Integer id) {
        return "delete success";
    }

    @PutMapping(value = "/student/{id}/{name}/{age}")
    public Student update(@PathVariable Integer id, @PathVariable String name,
                          @PathVariable Integer age) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        return student;
    }
}
