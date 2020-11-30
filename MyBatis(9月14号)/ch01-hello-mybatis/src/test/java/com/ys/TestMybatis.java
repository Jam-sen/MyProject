package com.ys;

import com.ys.domain.Student;
import com.ys.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestMybatis {
    //测设方法，测试功能
    @Test
    public void testInsert() throws IOException {
        //1.定义mybatis主配置文件的名称，从类路径的根开始（target/classes）
        String config = "mybatis.xml";
        //2.读取这个config表示的文件
        InputStream in = Resources.getResourceAsStream (config);
        //3.创建SqlSessionFactoryBuilder对象2
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder ();
        //4.创建SqlSessionFactory对象（将以读取mybatis主配置文件的输入流传入build参数）
        SqlSessionFactory factory = builder.build (in);
        //5.[重要]获取SqlSession对象（openSession方法中可以传入一个布尔值控制是否自动提交，true表示自动提交,默认为手动提交）
        SqlSession sqlSession = factory.openSession ();
        //6.[重要]指定要执行的sql语句的标识。sql映射文件中的namespace+"."+标签的id值
        String sqlId = "com.ys.dao.StudentDao"+"."+"insertStudent";
        //7.执行sql语句，通过sqlId找到语句
        Student student = new Student ();
        student.setAge (20);
        student.setId (1020);
        student.setEmile ("zhangfei@qq.com");
        student.setName ("张飞");
        int result = sqlSession. insert (sqlId,student);
        sqlSession.commit ();
        //8.输出结果
        System.out.println ("执行insert结果（插入条数）："+result);
        //9.关闭sqlSession对象,释放资源
        sqlSession.close ();
    }

    @Test
    public void testInsertForUtil() {
        //1.获取SqlSession对象，从SQLSessionFactory中获取SqlSession
        SqlSession sqlSession = MyBatisUtil.getSqlSession ();
        //2.指定要执行的sql语句的标识。sql映射文件中的namespace+"."+标签的id值
        String sqlId = "com.ys.dao.StudentDao"+"."+"insertStudent";
        //3.执行sql语句，通过sqlId找到语句
        Student student = new Student ();
        student.setAge (20);
        student.setId (1005);
        student.setEmile ("GuanYv@qq.com");
        student.setName ("关羽");
        int result = sqlSession. insert (sqlId,student);
        sqlSession.commit ();
        //4.输出结果
        System.out.println ("执行insert结果："+result);
        //5.关闭sqlSession对象
        sqlSession.close ();
    }
}
