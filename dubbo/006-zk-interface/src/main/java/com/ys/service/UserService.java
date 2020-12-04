package com.ys.service;

import com.ys.domain.User;

public interface UserService {
    //根据id查用户详情
    User queryUserById(Integer id,String userName);
}
