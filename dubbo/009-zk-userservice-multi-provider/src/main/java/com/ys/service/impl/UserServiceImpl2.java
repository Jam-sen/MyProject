package com.ys.service.impl;

import com.ys.domain.User;
import com.ys.service.UserService;

public class UserServiceImpl2 implements UserService {
    @Override
    public User queryUserById(Integer id, String userName) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName+"2");
        return user;
    }
}
