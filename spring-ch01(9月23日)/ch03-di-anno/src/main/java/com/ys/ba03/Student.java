package com.ys.ba03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myStudent")
public class Student {
    /*
    *   @value:简单类型的属性赋值
    *   属性：value是string类型的，表示简单类型的属性值
    *   位置：1.在属性定义的上面，无需set方法，推荐使用。
    *        2. 在set方法的上面。
    */
    @Value(value = "张飞")
    private String name;
    //因为就一个value属性，也可以将它省略
    @Value ("29")
    private Integer age;

    /*使用注解给引用类型赋值
     * @Autowired：spring框架提供的注解，实现引用类型的赋值。
     * spring中通过注解给引用类型赋值，使用的是自动注入原理，支持buName，byType
     * @Autowited:默认使用的byType自动注入。
     *
     *    位置：1）在属性定义的上面，无需set方法，推荐使用
     *          2）在set方法的上面。
     *
     */
    @Autowired
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
