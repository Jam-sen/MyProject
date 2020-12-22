package com.ys.springboot.service.impl;

import com.ys.springboot.dao.StudentMapper;
import com.ys.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Integer getStudentsCount() {
        return studentMapper.getStudentsCount();
    }
}
