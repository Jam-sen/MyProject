package com.ys.springboot.dao;

import com.ys.springboot.domain.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //作用是：扫描Dao接口到spring容器
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}