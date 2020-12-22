package com.ys.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ys.springboot.dao.StudentMapper;
import com.ys.springboot.domain.Student;
import com.ys.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Service(interfaceClass = StudentService.class, version = "1.0.0", timeout = 15000)
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer getStudentsCount() {
        Integer count = (Integer) redisTemplate.opsForValue().get("studentsCount");
        if (count == null) {
            count = studentMapper.getStudentsCount();
            redisTemplate.opsForValue().set("studentsCount",count,60, TimeUnit.SECONDS);
        }
        return count;
    }
}
