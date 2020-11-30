package com.ys.dao;

import com.ys.domain.Student;
import com.ys.vo.StudentAndClassVo;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    List<Map<Object, Object>> selectStudents();

    void insertStudent(Student student);

    Student selectById(int id);

    List<Map<String, Object>> selectStudentClass();

    List<StudentAndClassVo> selectStudentClass2();
}
