package com.ys;

import com.ys.ba06.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class 指定多个spring配置文件 {
    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext ("ba06/total.xml");
        Student student = (Student) ac.getBean ("myStudent");
        System.out.println (student);
    }
}
