package com.ys.springboot.service.impl;

import com.ys.springboot.dao.StudentMapper;
import com.ys.springboot.domain.Student;
import com.ys.springboot.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student getDetailById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }
}
