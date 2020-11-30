package com.ys.exception;
//定义异常父类
public class MyUserException extends Exception {
    public MyUserException() {
        super();
    }

    public MyUserException(String message) {
        super(message);
    }
}
