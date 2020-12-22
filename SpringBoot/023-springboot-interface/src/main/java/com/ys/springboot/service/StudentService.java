package com.ys.springboot.service;

import com.ys.springboot.domain.Student;

public interface StudentService {
    Student getStudentById(Integer id);

    Integer getStudentsCount();
}
