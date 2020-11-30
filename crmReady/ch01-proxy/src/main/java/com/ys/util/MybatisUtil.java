package com.ys.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private  static SqlSessionFactory sqlSessionFactory =null;
    static {
        InputStream inputStream=null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
    //获取SqlSessioin
    public static SqlSession getSqlSessioin(){
        SqlSession sqlSession = threadLocal.get();
        if (sqlSession == null) {
            threadLocal.set(sqlSessionFactory.openSession());
            sqlSession = threadLocal.get();
        }
        return sqlSession;
    }

    public static void myClose(SqlSession sqlSession) {
        sqlSession.close();
        threadLocal.remove();
    }
}

