package com.ys.springcloud.hystrix;

import com.ys.springcloud.model.User;
import com.ys.springcloud.service.TestService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义异常熔断器类并实现自定义的声明式服务消费接口
 * 为这个接口中所有的抽象方法提供熔断处理
 */
@Component
public class MyFallback implements TestService {
    @Override
    public String test() {
        return "test服务熔断";
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
}
