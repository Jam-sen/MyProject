package com.ys.service.impl;

import com.ys.dao.UserDao;
import com.ys.domain.Student;
import com.ys.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

@org.springframework.stereotype.Service("service2")
public class Service2 implements Service {
    @Autowired
    Student student;
    @Resource(name = "oracleDao")
    UserDao dao;

    @Override
    public void insert() {
        dao.insert (student);
    }
}
