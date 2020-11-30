package com.ys.service;

import com.ys.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> selectStudents();

    void insertStudent(Student student);
}
