package com.ys.test;

import com.ys.domain.Student;
import com.ys.handler.MyInvocationHandler;
import com.ys.service.StudentService;
import com.ys.service.impl.StudentServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        StudentService studentService = new StudentServiceImpl();
        InvocationHandler invocationHandler = new MyInvocationHandler(studentService);
        StudentService proxyInstance = (StudentService)Proxy.newProxyInstance(studentService.getClass().getClassLoader(), studentService.getClass().getInterfaces(), invocationHandler);
        Student student = new Student();
        student.setName("马云");
        student.setAge(54);
        student.setId(3);
        List<Student> result = proxyInstance.selectStudents();
        for (Student s :
                result) {
            System.out.println("--->"+s);
        }
    }
}
