package com.ys.service.impl;

import com.ys.domain.User;
import com.ys.service.UserService;
import com.ys.util.UUIDUtil;

public class UserServiceImpl implements UserService {
    @Override
    public User queryUserById(Integer id) {
        User user = new User();
        user.setAge(21);
        user.setId(UUIDUtil.getUUID());
        user.setUserName("张三");
        return user;
    }
}
