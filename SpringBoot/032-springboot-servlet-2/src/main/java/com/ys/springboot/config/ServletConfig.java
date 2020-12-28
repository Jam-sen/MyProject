package com.ys.springboot.config;

import com.ys.springboot.servlet.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;

@Configuration//该注解将此类定义为配置类（相当于一个xml配置文件）
public class ServletConfig {
    //@Bean是一个方法级别上的注解，主要用在配置类中，相当于一个<beans></beans>标签
    @Bean
    public ServletRegistrationBean myServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new
                ServletRegistrationBean(new MyServlet(), "/myServlet");
        return servletRegistrationBean;
    }
}
