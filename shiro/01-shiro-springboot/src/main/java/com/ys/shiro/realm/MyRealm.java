package com.ys.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的Realm用来实现具体的用户认证和授权操作
 *  父类AuthenticatingRealm 只负责用户认证（登录）
 *  父类AuthorizingRealm 负责用户认证（登录）和授权
 */
public class MyRealm extends AuthorizingRealm {

    /**
     * 用户认证的方法，这个方法不能手动调用，shiro会自动调用
     * @param authenticationToken 用户身份，这里存放着用户的账号和密码
     * @return 用户登录成功后的身份证明
     * @throws AuthenticationException 如果认证失败shiro会抛出各种异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //获取页面中传递的用户账号
        String username = usernamePasswordToken.getUsername();
        //获取页面中传递的用户密码（实际工作中一般不需要获取）
        String password = new String(usernamePasswordToken.getPassword());
        System.out.println(username + "---" + password);
        /**
         * 验证账户，此处应该访问数据库进行查询验证
         */
        //如果进入if说明账户不存在要抛出异常
        if (!"admin".equals(username) && !"zhangsan".equals(username) && !"user".equals(username)) {
            throw new UnknownAccountException();//抛出账号错误异常
        }
        if ("zhangsan".equals(username)) {
            throw new LockedAccountException();
        }


        /**
         *数据密码加密主要是为了防止数据从浏览器到后台服务器之间的数据传递时被篡改或被截获，因此应该在前台到后台的过程中进行加密，而我们这里是对浏览器中获取的明码和数据库中获取的数据进行加密，所以此处的数据加密没有意义，应该在前端页面进行传递时加密
         *  注意：
         *      建议浏览器传递数据时就是加密后的数据，数据库中也存放加密数据，并且我们必须保证前端传递的数据与数据库中的数据的加密次数和盐值完全相同，否则认证会失败。
         */
        //为当前用户键入的密码加密
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
//        hashedCredentialsMatcher.setHashIterations(1);
//        this.setCredentialsMatcher(hashedCredentialsMatcher);

        //为数据库中的密码加密（实际工作中不需要，因为用户注册时，数据库中会直接保存密文）
        Object obj = new SimpleHash("MD5", "123456", "", 1);
        /**
         * 创建密码认证对象，由shiro自动认证密码
         *  参数1：账号(用户名)
         *  参数2：数据库中读取出来的密码
         *  参数3：当前Realm的名字
         *  如果密码认证成功则返回用户身份对象，如果认证失败则shiro抛出异常
         */
        return new SimpleAuthenticationInfo(username,obj.toString(), getName());
    }

    /**
     * 用户授权方法，当用户登录认证通过后，每次访问需要授权的请求时，都将自动执行这个方法来完成授权操作。
     * 在这个方法中，也应该查询数据库来获取用户的所有角色和权限，并设置到shiro中
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权方法执行");
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();//获取用户的账号，根据账号来从数据库中获取数据
        //注意：由于每次访问需要授权的请求时，shiro每次都会执行这个方法进行授权，因为这里的数据是来自数据库中的，那么一定要控制好，不能每次都从数据库中获取数据，那样效率太低了
        Set<String> roles = new HashSet<>();//定义用户角色的set集合，这个集合应该来自数据库
        if ("admin".equals(primaryPrincipal)) {
            roles.add("admin");
            roles.add("user");
        } else if ("user".equals(primaryPrincipal)) {
            roles.add("user");
        }
        Set<String> permissions = new HashSet<>();//定义用户权限的Set集合，这个集合应该来自数据库
        if ("admin".equals(primaryPrincipal)) {
            permissions.add("admin:add");//'admin:add'只是一种命名风格，表示admin下的add功能
            permissions.add("admin:delete");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        authorizationInfo.addRoles(roles);
        return authorizationInfo;
    }
}
