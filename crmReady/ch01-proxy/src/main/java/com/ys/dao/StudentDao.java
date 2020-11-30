package com.ys.dao;

import com.ys.domain.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectStudents();

    void insertStudent(Student student);
}
