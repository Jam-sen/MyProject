package com.ys.crm.settings.dao;

import com.ys.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {
    User login(Map<String, String> map);
}
