package com.ys;

import com.ys.dao.StudentDao;
import com.ys.domain.Student;
import com.ys.service.StudentService;
import org.apache.ibatis.javassist.ClassPath;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest01 {
    @Test
    //测试dao对象，查询数据库
    public void test01(){
        String config = "ApplicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        String[] names = ac.getBeanDefinitionNames ();
        for (String name :
                names) {
            System.out.println ("容器中对象的名称"+name+"|"+ac.getBean (name).getClass ().getName ());
        }
        StudentDao studentDao = (StudentDao) ac.getBean ("studentDao");
        List<Student> students = studentDao.selectStudents ();
        for (Student student :
                students) {
            System.out.println (student);
        }

    }

    @Test
    //测试通过spring获取service接口实现类，调用dao接口代理对象的insert方法
    public void test02(){
        String config = "ApplicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        StudentService studentService = (StudentService) ac.getBean ("myService");
        Student student = new Student (1034,"马化腾","mahuateng@qq.com",48);
        int count = studentService.addStudent (student);
        //spring和mybatis整合在一起使用，事务是自动提交的。无需执行SqlSession.commit()。
        System.out.println (count);
    }

    @Test
    public void test03(){
        String config = "ApplicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext (config);
        StudentService studentService = (StudentService) ac.getBean ("myService");
        List<Student> students = studentService.queryStudents ();
        students.forEach (student -> System.out.println (student));
    }
}
