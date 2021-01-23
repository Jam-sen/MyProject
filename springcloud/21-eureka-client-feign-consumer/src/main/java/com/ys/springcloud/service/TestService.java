package com.ys.springcloud.service;

import com.ys.springcloud.hystrix.MyFallbackFactory;
import com.ys.springcloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @FeignClient 用于标记当前接口是一个Feign的声明式服务接口，Spring会为这个接口生成动态代理对象并放入spring容器（代替原先使用的RestTemplate对象）
 * 属性：
 * name 用于指定注册中心中的某个服务的名字（spring.application.name）
 * fallback 指定一个自定义的异常熔断器类，当使用声明式接口中的方法调用远程服务时，如果出现了异常则自动执行fallback所指定的这个类中的对应的方法来执行服务的降级。
 * fallbackFactory 指定一个自定义的异常熔断器类，与fallback指定的异常熔断器的区别是，这个熔断器类可以获取异常信息。
 */
//@FeignClient(name = "22-eureka-client-feign-provider",fallback = MyFallback.class)
@FeignClient(name = "22-eureka-client-feign-provider",fallbackFactory = MyFallbackFactory.class)
public interface TestService {
    /**
     * 定义抽象方法，并使用RequestMapping标记这个方法用于访问服务提供者
     * 其中@RequestMapping的参数value应该对应服务提供者中的某个服务的请求路径。
     *
     * @return 返回值为服务提供者返回的具体数据内容
     */
    @RequestMapping(value = "/test")
    String test();

    /**
     * 如果本次服务提供者的请求拥有参数，那么声明式服务接口的方法的形参前必须添加@RequestParam注解，否则无法传递参数到服务的提供者
     */
    @RequestMapping("test01")
    String test01(@RequestParam String name, @RequestParam Integer age);

    /**
     * 如果访问服务提供者时需要使用对象类型传递数据那么必须为这个参数添加一个@RequestBody注解，否则参数无法传递到服务提供者中，如果服务提供者也是使用对象来接受请求到参数，那么服务提供者的参数也需要添加@ReqeustBody
     */
    @RequestMapping("test02")
    String test02(@RequestBody User user);

    /**
     * 如果服务提供者返回的数据符合Json对象格式那么我们就可以使用一个实体类或Map接收响应数据
     */
    @RequestMapping("testReturnUser03")
    User testReturnUser03();

    /**
     * 如果服务提供者返回的数据符合Json对象数组，那么我们就可以使用一个List来接收数据。
     * Spring会根据我们的实际数据类型将Json转换成返回值对象
     *
     * @return
     */
    @RequestMapping("testReturnList04")
    List<User> testReturnList04();
}
