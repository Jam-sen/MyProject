package com.ys.service.impl;

import com.ys.domain.User;
import com.ys.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User queryById(Integer id) {
        User user = new User();
        user.setUserName("李四");
        user.setId(id);
        return user;
    }

    @Override
    public Integer queryAllUserCount() {
        return 52;
    }
}
