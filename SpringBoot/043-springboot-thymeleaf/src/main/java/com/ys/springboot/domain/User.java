package com.ys.springboot.domain;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String nick;
    private String phone;
    private String address;
}
