package com.ys.service.impl;

import com.ys.dao.StudentDao;
import com.ys.domain.Student;
import com.ys.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    //引用类型
    private StudentDao studentDao;

    //使用set注入，赋值
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        int num = studentDao.insertStudent (student);
        return num;
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = studentDao.selectStudents ();
        return students;
    }

}
