package com.ys.handler;

import com.ys.util.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=null;
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        System.out.println("before增强方法");
        try {
            result = method.invoke(target, args);
            sqlSession.commit();
            System.out.println("commit提交");
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            System.out.println("rollback回滚");
        }finally {
            System.out.println("关闭资源");
            MybatisUtil.myClose(sqlSession);
        }

        return result;
    }
}
