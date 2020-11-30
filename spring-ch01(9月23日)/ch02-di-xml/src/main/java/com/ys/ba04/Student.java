package com.ys.ba04;

public class Student {

    private String name;
    private int age;
    //声明一个引用类型属性
    private School school;

    public Student(String name, int age, School school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public Student() {
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setName(String name) {
        System.out.println ("setName方法执行了"+name);
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
                ", school=" + school +
                '}';
    }
}
