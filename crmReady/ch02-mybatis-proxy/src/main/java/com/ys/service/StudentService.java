package com.ys.service;

import com.ys.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<Map<Object, Object>> selectStudents();

    void insertStudent(Student student);

    Student selectById(int id);

    String service1(Student student);

    String selectStudentClass();
    String selectStudentClass2();

}
