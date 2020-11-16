package com.ys.crm.settings.service;

import com.ys.crm.exception.LoginException;
import com.ys.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User login(String loginName, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
