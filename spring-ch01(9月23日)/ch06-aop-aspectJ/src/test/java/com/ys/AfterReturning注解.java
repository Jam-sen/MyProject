package com.ys;

import com.ys.ba02.SomeService;
import com.ys.ba02.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AfterReturning注解 {
    @Test
    public void test02(){
        String config = "ApplicationContext02.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        SomeService someService = (SomeService) ac.getBean ("someService");
        User user = (User) someService.doOther ("张飞",22);
        System.out.println (user);
    }
}
