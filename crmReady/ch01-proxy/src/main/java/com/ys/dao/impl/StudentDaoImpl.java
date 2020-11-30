package com.ys.dao.impl;

import com.ys.dao.StudentDao;
import com.ys.domain.Student;
import com.ys.util.MybatisUtil;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> selectStudents() {
        return MybatisUtil.getSqlSessioin().selectList("com.ys.dao.StudentDao.selectStudents");
    }

    @Override
    public void insertStudent(Student student) {
        MybatisUtil.getSqlSessioin().insert("com.ys.dao.StudentDao.insertStudent",student);
    }
}
