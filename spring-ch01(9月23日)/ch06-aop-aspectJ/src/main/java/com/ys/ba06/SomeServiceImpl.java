package com.ys.ba06;

import com.ys.ba02.User;

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

    @Override
    public String doFirst(String name, Integer age) {
        System.out.println ("===业务方法doFirst()===");
        return "doFirst";
    }

    @Override
    public String doSecond(String name, Integer age) {
        return "执行业务方法doSecond"+(10/0);
    }

    @Override
    public void doThird() {
        System.out.println ("执行业务方法doThird()");
    }
}
