package com.ys.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, ModelMap model) {
        //获取权限操作对象，利用这个对象来完成登录操作
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                //执行登录，会自动调用Realm中的认证方法
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e) {
                model.addAttribute("message", "数据库无此账号");
                return "login";
            } catch (LockedAccountException e) {
                model.addAttribute("message", "此账号被锁定");
                return "login";
            } catch (AuthenticationException e) {
                model.addAttribute("message", "认证失败");
                return "login";
            }
        }
        return "redirect:/success";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //登出当前账号，清空shiro当前用户的缓存
        subject.logout();
        return "redirect:/";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    @RequestMapping("/noPermission")
    public String noPermission() {
        return "noPermission";
    }

    @RequestMapping("/detail")
    @ResponseBody
    public String detail() {
        return "用户详情";
    }

    /**
     * @RequeiresRoles 这个注解是shiro提供的，用于标记当前类或方法在访问时，必须需要什么样的角色
     *  属性：
     *      value：取值为String数组类型，用于指定访问时所需要的一个或多个角色名
     *      logical：取值为logical.AND或logical.OR,当指定多个角色时可以使用AND和OR来表示'并且'和'或者'（默认值为AND）
     *
     *  注意：
     *      Shiro中除了基于配置的权限验证和注解的权限验证意外，还支持基于方法验证的权限验证。
     *
     */
    @RequiresRoles(value = {"user"})
    @RequestMapping("/user/test")
    public @ResponseBody String userTest() {
        return "/user/test请求";
    }

    @RequestMapping("/admin/test")
    @RequiresRoles(value = {"admin"})
    public @ResponseBody String adminTest() {
        return "/admin/test请求";
    }

    /**
     * @RequeiresPermissions 用于标记当前方法在访问时需要什么样的权限，用法与@RequeiresRoles是相同的
     */
    @RequiresPermissions(value = {"admin:add"})
    @RequestMapping("/add")
    public @ResponseBody String add() {
        return "/add请求执行";
    }

    /**
     * 使用方法验证的权限验证
     */
    @RequestMapping("/delete")
    public @ResponseBody String delete() {
        Subject subject = SecurityUtils.getSubject();
        String[] roles = {"admin"};
        subject.checkRoles(roles);//验证当前用户是否拥有指定的角色
        String[] permissions = {"admin:delete"};
        subject.checkPermissions(permissions);//验证当前用户是否拥有指定的权限

        return "/delete请求执行";
    }
}