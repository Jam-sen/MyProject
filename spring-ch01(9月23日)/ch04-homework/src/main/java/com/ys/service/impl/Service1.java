package com.ys.service.impl;

import com.ys.dao.UserDao;
import com.ys.domain.Student;
import com.ys.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@org.springframework.stereotype.Service("service1")
public class Service1 implements Service {
    @Autowired
    Student student;
    @Resource(name = "mysqlDao")
    UserDao userDao;

    @Override
    public void insert() {
        userDao.insert (student);
    }

}
