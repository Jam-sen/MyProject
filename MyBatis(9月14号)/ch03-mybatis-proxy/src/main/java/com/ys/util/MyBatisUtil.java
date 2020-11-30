package com.ys.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    //定义SqlSessionFactory
    private static SqlSessionFactory sqlSessionFactory = null;
    static {
        //使用静态块创建SqlSessionFactory
        try {
            //读取配置文件
            InputStream inputStream = Resources.getResourceAsStream ("mybatis.xml");
            //创建SqlSessionFactory对象
            sqlSessionFactory = new SqlSessionFactoryBuilder ().build (inputStream);
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public static SqlSession getSqlSession() {
        SqlSession sqlSession = null;
        if (sqlSessionFactory != null) {
            //获取SqlSession对象
            sqlSession = sqlSessionFactory.openSession ();
        }
        return sqlSession;
    }
}
