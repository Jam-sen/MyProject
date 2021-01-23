package com.ys.springcloud.hystrix;

import com.ys.springcloud.model.User;
import com.ys.springcloud.service.TestService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义异常熔断器类，并实现Hystrix的降级回调的父接口
 * 注意：
 * 这个父接口的泛型很重要！这个泛型决定当前类要为哪个声明式接口提供异常熔断。
 */
@Component
public class MyFallbackFactory implements FallbackFactory<TestService> {
    /**
     * create()方法来自父接口，用于返回一个父接口中泛型的对象，作用是当这个泛型对象出现异常以后将使用create方法返回的这个对象来进行降级
     */
    @Override
    public TestService create(Throwable throwable) {
        //使用匿名内部类来创建TestService声明式服务接口的熔断对象
        return new TestService() {
            @Override
            public String test() {
                return "test方法被熔断了" + throwable.getClass() + "->" + throwable.getMessage() + "->" + throwable.getCause();
            }

            @Override
            public String test01(String name, Integer age) {
                return null;
            }

            @Override
            public String test02(User user) {
                return null;
            }

            @Override
            public User testReturnUser03() {
                return null;
            }

            @Override
            public List<User> testReturnList04() {
                return null;
            }
        };
    }
}
