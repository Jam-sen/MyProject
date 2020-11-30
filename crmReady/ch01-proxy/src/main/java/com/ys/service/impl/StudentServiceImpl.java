package com.ys.service.impl;

import com.ys.dao.StudentDao;
import com.ys.dao.impl.StudentDaoImpl;
import com.ys.domain.Student;
import com.ys.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();
    @Override
    public List<Student> selectStudents() {
        return studentDao.selectStudents();
    }

    @Override
    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }
}
