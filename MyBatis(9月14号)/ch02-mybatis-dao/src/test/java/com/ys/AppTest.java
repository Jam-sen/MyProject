package com.ys;

import static org.junit.Assert.assertTrue;

import com.ys.dao.impl.StudentDaoImpl;
import com.ys.domain.Student;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSelectStudents() {
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl ();
        List<Student> list = studentDaoImpl.selectStudents ();
        for (Student student:list) {
            System.out.println (student);
        }
    }
    @Test
    public void testInsertStudent(){
        StudentDaoImpl studentDaoImpl = new StudentDaoImpl ();
        Student student = new Student ();
        student.setName ("张飞");
        student.setId (1014);
//        student.setEmile ("zhangfei@qq.com");
        student.setAge (25);
        System.out.println (student);
        System.out.println ("插入条数："+studentDaoImpl.insertStudent (student));

    }
}
