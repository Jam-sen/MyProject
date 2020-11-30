package com.ys;

import com.ys.ba05.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class After注解 {
    @Test
    public void test05(){
        String config = "ApplicationContext05.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        SomeService someService = (SomeService) ac.getBean ("someService");
        someService.doThird ();

    }
}
