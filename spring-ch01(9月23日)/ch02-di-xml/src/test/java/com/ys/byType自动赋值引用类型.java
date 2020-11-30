package com.ys;

import com.ys.ba05.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class byType自动赋值引用类型 {
    @Test
    public void test01(){
        ApplicationContext ac =  new ClassPathXmlApplicationContext ("ba05/applicationContext.xml");
        Student student = (Student) ac.getBean ("myStudent");
        System.out.println (student);
    }
}
