package com.ys.springcloud.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ys.springcloud.hystrix.MyHystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;

    /**
     * @HystrixCommand 注解的作用是标记当前控制器方法启用Hystrix的熔断机制，当我们调用远程服务时，如果远程服务出现了异常或超时（指定时间内没有返回响应），就会自动进行熔断。
     * 属性：
     *  fallbackMethod：用于指定一个本地方法的名称，当服务熔断以后就会调用这个方法来替代服务提供者的响应信息。
     * @return
     */
    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping("/test")
    public String test() {
        String result = restTemplate.getForObject("http://17-eureka-client-hystrix-provider/test", String.class);
        return "带有Hystrix的服务消费者--->"+result;
    }

    /**
     * 属性：
     *  commandProperties 用于设置熔断器的一些属性
     *      @HystrixProperty 注解用于指定一个熔断器的属性：
     *          execution.isolation.thread.timeoutInMilliseconds 表示熔断的超时时间，如果服务在指定时间内没有返回，则表示超时需要熔断。（默认为1000毫秒）
     *          value用于指定属性的值，1500表示1.5秒
     */
    @HystrixCommand(fallbackMethod = "error",commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
    @RequestMapping("/test02")
    public String test02() {
        String result = restTemplate.getForObject("http://17-eureka-client-hystrix-provider/test02", String.class);
        return "带有Hystrix的服务消费者--->"+result;
    }

    @HystrixCommand(fallbackMethod = "error")
    @RequestMapping("/test03")
    public String test03() {
        String result = restTemplate.getForObject("http://17-eureka-client-hystrix-provider/test03", String.class);
        return "带有Hystrix的服务消费者test03--->"+result;
    }

    /**
     * 属性：
     *  ignoreExceptions 用于指定熔断时忽略某些异常，如果程序出现了指定的异常以后将不进行熔断，而是将错误直接抛出，抛给服务的调用者。（这个调用者可能是用户，也可能是其他服务）
     *  注意：如果需要忽略服务提供者抛出的异常，那么我们需要过滤掉HttpServerErrorException.InternalServerError.class
     */
    @HystrixCommand(fallbackMethod = "error",ignoreExceptions = {HttpServerErrorException.InternalServerError.class})
    @RequestMapping("/test04")
    public String test04() {
        String result = restTemplate.getForObject("http://17-eureka-client-hystrix-provider/test03", String.class);
        return "带有Hystrix的服务消费者test03--->"+result;
    }

    @RequestMapping("/test05")
    public String test05() {
        MyHystrixCommand myHystrixCommand = new MyHystrixCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")), restTemplate, "http://17-eureka-client-hystrix-provider/test05");
        /**
         * 执行请求并获取响应结果，execute()方法后台会自动调用run方法访问服务的提供者，如果服务提供者抛出异常则自动调用getFallback方法来返回相应数据
         */
        String result = (String) myHystrixCommand.execute();
        return "带有Hystrix的服务消费者test05--->"+result;
    }


    /**
     * 降级方法：
     * 服务的降级方法，当我们的某个控制器方法被熔断以后就不能正常返回给用户一个响应结果，因此在服务被熔断后会自动调用这个服务的降级方法，来为用户返回一个本地的响应信息，这个信息虽然不是真正的处理结果，但是至少可以返回一个友好提示，避免用户看到错误内容或无限等待。只要被监控的控制器方法出现异常，就会被自动熔断，调用降级方法，无论是控制器方法本身还是远程服务出现异常。
     *
     *  参数1 Throwable：当降级方法被调用时，参数Throwable就会被Spring注入一个异常对象（控制器方法执行时抛出的异常）
     *
     *  注意：
     *      如果是服务提供者抛出异常，那么Spring会统一注入一个HttpServerErrorException$InternalServerError异常对象，获取的异常并不是我们服务提供者实际抛出的异常对象类型；如果是消费者本地抛出的异常，那么Spring会注入抛出异常对象本身。
     */
    public String error(Throwable throwable) {
        System.out.println(throwable.getClass());
        System.out.println(throwable.getMessage());
        return "服务被熔断了" + throwable.getMessage();

    }
}
