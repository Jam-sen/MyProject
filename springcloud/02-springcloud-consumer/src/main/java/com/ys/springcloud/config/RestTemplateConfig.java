package com.ys.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    /**
     * 在spring容器中定义restTemplate对象
     * @Bean 标记当前方法是一个Spring配置方法，用于模拟Spring配置文件中的Bean标签
     * 其中方法的返回值相当于Bean标签的class
     * 其中方法的名字相当于Bean标签的id
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
