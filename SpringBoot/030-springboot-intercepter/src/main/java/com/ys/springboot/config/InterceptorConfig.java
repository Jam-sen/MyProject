package com.ys.springboot.config;

import com.ys.springboot.intercepter.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //定义此类为配置文件（相当于之前的xml配置文件）
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] include = {
                "/user/**"
        };
        String[] exclude = {
                "/user/out","/user/error","/user/login"
        };
        InterceptorRegistration registration = registry.addInterceptor(new UserInterceptor());
        registration.addPathPatterns(include).excludePathPatterns(exclude);
    }
}
