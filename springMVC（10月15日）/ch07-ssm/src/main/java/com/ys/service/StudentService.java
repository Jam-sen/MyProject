package com.ys.service;

import com.ys.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    int addStudent(Student student);

    List<Student> findStudents();

}
