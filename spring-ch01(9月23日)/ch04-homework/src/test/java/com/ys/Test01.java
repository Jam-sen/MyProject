package com.ys;

import com.ys.service.Service;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Test01 {
    @Test
    public void test01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext ("ApplicationContext.xml");
        Service service = (Service) ac.getBean ("service1");
        service.insert ();

    }

}
