package com.ys.ba01;

public class Student {

    private String name;
    private int age;

    public void setName(String name) {
        System.out.println ("setName方法执行了" + name);
        this.name = name;
    }
    public void setAge(int age) {
        System.out.println ("setAge方法执行了"+age);
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
