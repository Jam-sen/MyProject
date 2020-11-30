package com.ys;

import com.ys.service.SomeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test01(){
        //使用spring容器创建的对象
        //1.指定spring配置文件的名称
        String config = "ApplicationContext.xml";

        //2.创建表示spring容器的对象，ApplicationContext
        //ApplicationContext(接口)就表示Spring容器，通过容器获取对象
        //classPathXmlApplicationContext：表示从类路径中加载spring的配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);

        //3.getBean("配置文件中的bean的id"):从容器中获取某个对象的方法
        SomeService service = (SomeService)ac.getBean ("someService");

        //4.使用spring创建好的对象
        service.doSome ();

        //*使用spring提供的getBeanDefinitionCount方法，可获取容器中定义的对象的数量
        int count = ac.getBeanDefinitionCount ();
        System.out.println ("bean定义数量"+count);

        //*容器中每个定义的对象的名称
        String[] names = ac.getBeanDefinitionNames ();
        for (int i = 0;i<names.length;i++){
            System.out.println ("第"+(i+1)+"个bean的名字是："+names[i]);
        }
    }
}
