package com.ys.crm.settings.service.impl;

import com.ys.crm.exception.LoginException;
import com.ys.crm.settings.dao.UserDao;
import com.ys.crm.settings.domain.User;
import com.ys.crm.settings.service.UserService;
import com.ys.crm.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackFor = LoginException.class,propagation = Propagation.REQUIRED)
    public User login(String loginName, String loginPwd, String ip) throws LoginException {
        Map<String, String> map = new HashMap<>();
        map.put("loginName", loginName);
        map.put("loginPwd",loginPwd);
        User user = userDao.login(map);
        //如果user为null，则表明账号密码有误
        if (user == null) {
            throw new LoginException("账号密码错误");
        }
        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        int k = expireTime.compareTo(currentTime);
        if (k < 0) {
            throw new LoginException("账号已失效");
        }

        //判断锁定状态
        String lockState = user.getLockState();
        if ("0".equals(lockState)) {
            throw new LoginException("账号被锁定，请联系管理员");
        }

        //判断IP地址
        String allowIps = user.getAllowIps();
        if (!allowIps.contains(ip)) {
            throw new LoginException("ip地址受限");
        }
        return user;
    }
}
