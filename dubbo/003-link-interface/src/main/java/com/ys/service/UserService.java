package com.ys.service;


import com.ys.domain.User;

public interface UserService {
    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    User queryById(Integer id);

    /**
     * 查询用户总人数
     * @return
     */
    Integer queryAllUserCount();
}
