package com.ys;

import com.ys.ba03.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

public class 构造注入 {
    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext ("ba03/ApplicationContext.xml");
        Student student = (Student)ac.getBean ("myStudent");
        System.out.println (student);
    }
    @Test
    public void test02() throws IOException {
        ApplicationContext ac = new ClassPathXmlApplicationContext ("ba03/ApplicationContext.xml");
        File file = (File) ac.getBean ("myFile");
        System.out.println (file.getName ());
        InputStream inputStream = new FileInputStream (file);
        int count = inputStream.available ();
        System.out.println (count);
        byte[] bytes = new byte[count];
        inputStream.read (bytes);
        System.out.println (new String (bytes));
    }
}
