package com.ys.springboot.service.impl;

import com.ys.springboot.dao.StudentMapper;
import com.ys.springboot.domain.Student;
import com.ys.springboot.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    @Transactional
    public int updateById(Student student) {
        int result = studentMapper.updateByPrimaryKeySelective(student);
        int a = 10 / 0;
        return result;
    }

}
