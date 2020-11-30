package com.ys;

import com.ys.ba02.School;
import com.ys.ba02.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class set设值方式给引用类型赋值 {

    @Test
    public void test02(){
        //传统手工方式
        Student student = new Student ();
        student.setAge (20);
        student.setName ("lisi");
        School school = new School ();
        school.setAddress ("北京");
        school.setName ("动力节点");
        student.setSchool (school);
        System.out.println (student);
    }
    @Test
    public void test03(){
        //使用Spring给引用类对象赋值
        ApplicationContext ac = new ClassPathXmlApplicationContext ("ba02/applicationContext.xml");
       Student student = (Student) ac.getBean ("myStudent");
        System.out.println (student);
    }
}
