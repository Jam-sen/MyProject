package com.ys.dao.impl;

import com.ys.dao.UserDao;
import com.ys.domain.Student;
import org.springframework.stereotype.Repository;

@Repository("mysqlDao")
public class MysqlDao implements UserDao {

    @Override
    public int insert(Student student) {
        System.out.println ("Mysql的insert方法执行,插入学生信息："+student);
        return 1;
    }
}
