package com.ys.service.impl;

import com.ys.dao.StudentDao;
import com.ys.domain.Student;
import com.ys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource(name = "studentDao")
    private StudentDao dao;

    @Override
    public int addStudent(Student student) {
        return dao.insertStudent(student);
    }

    @Override
    public List<Student> findStudents() {
        return dao.selectStudents();
    }
}
