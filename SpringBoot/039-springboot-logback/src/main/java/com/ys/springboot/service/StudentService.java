package com.ys.springboot.service;

import com.ys.springboot.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    Integer getStudentsCount();

}
