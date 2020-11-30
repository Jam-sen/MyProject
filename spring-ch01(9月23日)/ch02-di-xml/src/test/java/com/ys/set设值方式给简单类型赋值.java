package com.ys;

import com.ys.ba01.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class set设值方式给简单类型赋值 {
    @Test
    public void test01() {
        String config = "ba01/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        Student student = (Student) ac.getBean ("myStudent");
        System.out.println (student);

        Date myDate = (Date) ac.getBean ("myDate");
        System.out.println (myDate);
    }
}
