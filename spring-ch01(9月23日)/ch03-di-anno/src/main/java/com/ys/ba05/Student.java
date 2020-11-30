package com.ys.ba05;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("myStudent")
public class Student {

    @Value(value = "张飞")
    private String name;
    //因为就一个value属性，也可以将它省略
    @Value ("29")
    private Integer age;

    /**
     * 引用类型
     * @Resource ：来自jdk中的注解，spring框架提供了对这个注解的功能支持，可以使用它给引用类型赋值，使用的也是自动注入的原理，支持byName、byType。默认的是byName。
     *  位置：1.在属性定义的上面，无需set方法，推荐使用
     *          2.在set方法上面
     */
    //使用Resource注解给引用类型属性赋值时，默认显示用byName方式自动注入，如果buName赋值失败，再使用byType方式。
    @Resource
    private School school;



    public Student(){
        System.out.println ("student无参构造执行");
    }

    public void setName(String name) {
        this.name = name;
    }

    //也可以将注解放在set方法上,spring会优先使用set方法赋值。（这种方法用的比较少）
    @Value ("50")
    public void setAge(Integer age) {
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
