package com.ys.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义网关过滤器并继承过滤器父类
 */
@Component
public class AuthFilter extends ZuulFilter {
    /**
     * @return 返回值用于决定当前过滤器的类型（执行时机）
     *  返回"pre"表示当前过滤器为前置过滤器，需要在执行转发（访问服务提供者）前执行，通常用于做身份认证。
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * @return 过滤器的序号，如果有多个同类型的过滤器，那么根据这个返回值大小决定执行的先后顺序。
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @return 当前过滤器是否启用，返回true表示启用当前过滤器，false则表示不启用
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体执行方法
     * @return 返回值目前版本没有特殊作用，因此可以写null
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println(10/0);
        //获取当前请求上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        //通过请求上下文对象获取用户请求对象
        HttpServletRequest request = requestContext.getRequest();
        //获取请求中的请求参数token（身份令牌用于验证请求身份是否合法）
        String token = request.getParameter("token");
        if (request.getRequestURI().contains("gateway")) {
            return null;
        }
        //验证令牌有效性。注意：实际工作中这里应该到数据库中去验证令牌的合法性
        if (token == null || !"123".equals(token)) {
            //设置为false表示请求不继续执行（不转发给服务提供者）
            requestContext.setSendZuulResponse(false);
            //设置响应编码为401,表示权限不足；也可设置为500或其他编码（根据实际情况设定）
            requestContext.setResponseStatusCode(401);
            //设置响应类型以及编码格式
            requestContext.addZuulResponseHeader("content-Type", "text/html;charset=utf-8");
            requestContext.setResponseBody("非法请求");
        } else {
            System.out.println("请求合法继续执行请求，准备进入服务或下一个过滤器");
        }
        return null;
    }
}
