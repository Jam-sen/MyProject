package com.ys.dao;

import com.ys.domain.Student;

import java.util.List;

//接口操作student表
public interface StudentDao {
    //查询student表的所有数据
    List<Student> selectStudents();

    /**
     * 插入数据方法
     * @param student
     * @return插入成功条数
     */
    int insertStudent(Student student);


}
