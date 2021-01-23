package com.ys.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * @Bean 在spring容器中定义一个Rest模版对象
     * @LoadBalanced 注解用于标记当前创建的RestTemplate使用Ribbon的负载均衡访问服务器的提供者，当使用了Eureka注册中心后必须使用这个注解，否则无法正常获取服务，默认情况下Ribbon的负载均衡策略是轮询的。
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
