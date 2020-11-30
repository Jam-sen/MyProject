package com.ys.exception;

//表示当用户的姓名有异常，跑出NameException
public class NameException extends MyUserException{
    public NameException() {
        super();
    }

    public NameException(String message) {
        super(message);
    }
}
