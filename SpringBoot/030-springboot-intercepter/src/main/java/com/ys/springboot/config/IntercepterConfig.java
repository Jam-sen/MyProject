package com.ys.springboot.config;

import com.ys.springboot.intercepter.UserIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //定义此类为配置文件（相当于之前的xml配置文件）
public class IntercepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] include = {
                "/user/**"
        };
        String[] exclude = {
                "/user/out","/user/error","/user/login"
        };
        registry.addInterceptor(new UserIntercepter()).addPathPatterns(include).excludePathPatterns(exclude);
    }
}
