package com.ys.common.utils;

public enum StatusCode {
    SUCCESS_CODE(20000),
    ERROR_CODE(20001);

    private Integer statusCode;

    StatusCode(int i) {
        this.statusCode = i;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
