package com.ys.dao.impl;

import com.ys.dao.UserDao;
import com.ys.domain.Student;
import org.springframework.stereotype.Repository;


@Repository("oracleDao")
public class OracleDao implements UserDao {
    @Override
    public int insert(Student student) {
        System.out.println ("OracleDao的insert方法执行,插入学生信息："+student);
        return 1;
    }
}
