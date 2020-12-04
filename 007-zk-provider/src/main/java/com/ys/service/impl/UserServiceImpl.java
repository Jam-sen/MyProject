package com.ys.service.impl;

import com.ys.domain.User;
import com.ys.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User queryUserById(Integer id,String userName) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        return user;
    }
}
