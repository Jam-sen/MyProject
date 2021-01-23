package com.ys.springcloud.controller;

import com.ys.springcloud.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作为springcloud的服务消费者，我们可以返回任何数据，这里我们使用了RestController仅仅只是为了测试方便，并不是唯一标准
 */
@RestController
public class TestController {
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/getForEntity01")
    public String getForEntity01() {
        /**
         * 以get方式提交请求
         * 参数1：要调用的服务的地址，即服务提供者提供的http://01-SPRINGCLOUD-SERVICE-PROVIDER/service/hello接口地址，注意这里是通过服务名调用而不是服务地址，如果改为服务地址就无法使用Ribbon实现客户端负载均衡了。
         * 参数2：决定本次请求响应后，将响应结果转换成什么样类型的数据，如果服务提供者返回的数据格式是一个符合Json格式的对象类型数据，那么参数2可以指定一个实体类或Map集合，用于将这个Json数据转换成对应的数据类型
         *  注意：
         *      如果服务提供者返回的数据符合Json对象格式，那么Spring就会将这个数据转换成参数2所指定的数据类型
         */
        ResponseEntity<User> result = restTemplate.getForEntity("http://localhost:8081/getForEntity01", User.class);
        System.out.println(result.getStatusCode());//获取状态码，例如：200、404等等
        System.out.println(result.getHeaders());//获取响应头
        User user = result.getBody();//获取具体的响应数据，具体类型取决于getForEntity方法的参数2
        user.setName("lisi");
        return "springcloud消费者-->" + user;
    }

    @RequestMapping(value = "/getForEntity02")
    public String getForEntity02() {
        /**
         * 注意：
         *  1、如果服务提供者返回的是一个Json数组的数据，那么我们消费者应该使用List集合来接收数据，但是集合中的泛型无论写什么类型，Spring都不会自动进行转换，getForEntity()方法返回的集合中的数据全部都是Map集合（LinkedHashMap集合）
         */
        ResponseEntity<List> result = restTemplate.getForEntity("http://localhost:8081/getForEntity02", List.class);
        System.out.println(result.getStatusCode());//获取状态码，例如：200、404等等
        System.out.println(result.getHeaders());//获取响应头
        List userList = result.getBody();//获取具体的响应数据，具体类型取决于getForEntity方法的参数2
        System.out.println(userList.get(0).getClass());
        return "springcloud消费者-->" + userList;
    }

    @RequestMapping(value = "/getForEntityParams01")
    public String getForEntityParams01() {
        /**
         * 请求路径中的{0}、{1}、{2}表示请求地址路径中的占位符，需要后期为这些占位符动态的进行赋值
         * getForEntity()方法的参数3，表示请求地址路径中的动态参数，作用是为占位符号进行赋值，类型为可变长度的Object类型
         *  注意：
         *      1、占位符中的0、1、2分别对应动态参数数组中的索引值，用于通知Spring将数组中的数据赋值到对应的占位符
         *      2、这种传递必须要清楚的知道参数数组中的每一个元素的类型以及含义，因此容易出现传值的错误，参数过多时不推荐使用。
         */
        //定义请求路径
        String url = "http://localhost:8081/getForEntityParams01?id={0}&name={1}&age={2}";
        //定义请求参数数组
        Object[] params = {2L, "zhaoliu", 24};
        ResponseEntity<User> result = restTemplate.getForEntity(url, User.class, params);
        System.out.println(result.getStatusCode());//获取状态码，例如：200、404等等
        System.out.println(result.getHeaders());//获取响应头
        User user = result.getBody();//获取具体的响应数据，具体类型取决于getForEntity方法的参数2
        return "springcloud消费者-->" + user;
    }

    @RequestMapping(value = "/getForEntityParams02")
    public String getForEntityParams02() {
        /**
         *  请求路径中的{id}、{name}、{age}表示请求地址路径中的占位符，需要后期为这些占位符动态的进行赋值
         *  getForEntity()方法的参数3，表示请求地址路径中的动态参数，作用是为占位符号进行赋值，类型为Map集合
         *   注意：
         *       1、占位符中的id、name、age分别对应Map集合中的key，用于通知Spring将key所对应的数据赋值到对应的占位符中
         *       2、由于SpringMVC中可以直接使用Map集合作为控制器的形参来接收请求中的参数数据，因此不需要特意定义一个Map集合来作为访问服务提供者的参数对象。
         *       3、使用时一定要注意，Map集合中的每一个key的名字，否则无法成功传值，如果访问服务提供者需要传递多个参数，推荐使用Map集合。
         *       4、地址路径占位符，不仅仅可以出现在请求的参数中，也可以出现在请求路径的任何一个位置。例如域名的位置、请求路径的位置： http://{domain}:{port}/{requestPath}?id={id}&name={name}&age={age}
         */
        //定义请求路径
        String url = "http://localhost:8081/getForEntityParams01?id={id}&name={name}&age={age}";
        //定义请求参数Map集合
        Map<String, Object> params = new HashMap<>();
        params.put("id", 3L);
        params.put("name", "王武");
        params.put("age", 25);
        ResponseEntity<User> result = restTemplate.getForEntity(url, User.class, params);
        System.out.println(result.getStatusCode());//获取状态码，例如：200、404等等
        System.out.println(result.getHeaders());//获取响应头
        User user = result.getBody();//获取具体的响应数据，具体类型取决于getForEntity方法的参数2
        return "springcloud消费者-->" + user;
    }

    @RequestMapping(value = "/getForObject")
    public String getForObject() {
        /**
         * getForObject()方法与getForEntity()方法的使用方式完全相同，都是对应后台GetMapping或RequestMapping
         *  区别在于：getForObject是直接将服务提供者返回的数据以参数2所指定的类型进行返回，不会返回ResponseEntity对象
         *  如果不需要获取提供者响应的头文件和响应状态码时，完全可以使用getForObject()方法替代getFoeEntity()
         */
        String url = "http://localhost:8081/getForObject";
        User user = restTemplate.getForObject(url, User.class);
        return "消费者-->" + user;
    }

    @PostMapping(value = "/postForObject")
    public String postForObject() {
        /**
         * postForObject相关方法与get相关方法非常类似，也有ForObject和ForEntity两套重载
         * 区别在于：post相关方法对应服务提供者的PostMapping或RequestMapping , post相关方法是以Post方式提交请求，访问服务的提供者
         *  post相关方法：
         *      参数1：请求的地址路径
         *      参数2：请求的具体参数对象，如果没有参数可以写null，通常使用post方式不会没有参数
         *      参数3：本次响应的具体数据类型
         *      参数4：本次请求的地址路径动态数据，取值为可变长度的Object类型数据，或Map集合类型数据，具体参考get相关方法。
         *      注意：
         *          1、参数2为本次请求的具体参数数据，参数类型为Object，但是却不能随意传递参数，参数的取值具体为Map集合，如果是一个普通的Map集合，那么value必须是一个List的泛型。否则可以选择Map集合的间接子类LinkedMultiValueMap，这个集合的value泛型就是一个List集合
         *          2、post提交的请求速度相对要慢于get方式，因此在非必要情况下建议使用get方式
         */
        String url = "http://localhost:8081/postForObject";
        //定义请求参数对象
        LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap();
        //设置数据到请求参数对象中。如果key不存在则直接设置数据到这个key，如果key已经存在则将当前数据设置到这个key所对应的list的尾部。
        linkedMultiValueMap.add("id", 5L);
        linkedMultiValueMap.add("name", "wangwu");
        linkedMultiValueMap.add("age", 26);
        User result = restTemplate.postForObject(url, linkedMultiValueMap, User.class);
        return "消费者-->" + result;
    }

    @PutMapping(value = "/put")
    public String put() {
        /**
         * put方法以post方式提交请求，对应服务提供者的PutMapping
         *  参数1：具体的请求地址路径
         *  参数2：具体的请求参数对象
         *  参数3：地址栏动态参数
         *      注意：
         *          1、put方法主要针对于数据的修改，这个方法没有返回值，不能获取响应的结果，因此我们不知道本次服务是否调用成功，所以除非必要情况不建议使用put方式。
         */
        String url = "http://localhost:8081/put";
        LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap();
        linkedMultiValueMap.add("id",6L);
        linkedMultiValueMap.add("name","胡八一");
        linkedMultiValueMap.add("id", 28);
        restTemplate.put(url, linkedMultiValueMap);
        return "服务消费者--执行了put请求";
    }
    
    @DeleteMapping(value = "/delete")
    public String delete() {
        /**
         * delete方法以get方式提交请求，对应服务提供者的DeleteMapping用于数据的删除
         *  参数1：具体的请求地址路径
         *  参数2：地址路径的动态参数
         *      注意：
         *          1、delete方法针对于数据删除，无法获取相应的结果，因此非必要情况下不建议使用
         */
        String url = "http://localhost:8081/delete?id={0}";
        Map param = new HashMap();
        param.put("id", 9L);
        restTemplate.delete(url, param);
        return "服务消费者--执行了delete请求";
    }
}
