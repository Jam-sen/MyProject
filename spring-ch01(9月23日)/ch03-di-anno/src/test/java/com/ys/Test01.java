package com.ys;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
    @Test
    public void test01(){
        String config = "ApplicationContext-back.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        System.out.println (ac.getBean ("myStudent"));
    }
}
