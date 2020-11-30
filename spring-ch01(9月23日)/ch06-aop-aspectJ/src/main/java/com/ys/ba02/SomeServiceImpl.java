package com.ys.ba02;

//目标类
public class SomeServiceImpl implements SomeService {

    @Override
    public void doSome(String name, Integer age) {
        //给doSome方法增加一个功能，在doSome（）方法执行之前，输出方法的执行时间
    System.out.println ("===目标方法doSome执行===");
    }

    @Override
    public Object doOther(String name, Integer age) {
        System.out.println ("===目标方法doOther()===");
        User user = new User ();
        user.setAge (age);
        user.setName (name);
        return user;
    }
}
