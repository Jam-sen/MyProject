package com.ys.domain;

public class Student {
    Integer id;
    String name;
    String emile;
    Integer age;

    public Student() {
    }

    public Student(Integer id, String name, String emile, Integer age) {
        this.id = id;
        this.name = name;
        this.emile = emile;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmile() {
        return emile;
    }

    public void setEmile(String emile) {
        this.emile = emile;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emile='" + emile + '\'' +
                ", age=" + age +
                '}';
    }
}
