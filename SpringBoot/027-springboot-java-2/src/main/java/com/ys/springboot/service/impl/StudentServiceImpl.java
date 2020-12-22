package com.ys.springboot.service.impl;

import com.ys.springboot.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public String sayHello(String massage) {
        return "say:"+massage;
    }

}
