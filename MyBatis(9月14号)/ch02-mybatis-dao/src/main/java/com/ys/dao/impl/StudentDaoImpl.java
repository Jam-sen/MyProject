package com.ys.dao.impl;

import com.ys.dao.StudentDao;
import com.ys.domain.Student;
import com.ys.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> selectStudents() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession ();
        List<Student> list = sqlSession.selectList ("com.ys.dao.StudentDao.selectStudents");
        sqlSession.close ();
        return list;
    }

    @Override
    public int insertStudent(Student student) {
        SqlSession sqlSession = MyBatisUtil.getSqlSession ();
        int result = sqlSession.insert ("com.ys.dao.StudentDao.insertStudent",student);
        sqlSession.commit ();
        sqlSession.close ();
        return result;
    }
}
