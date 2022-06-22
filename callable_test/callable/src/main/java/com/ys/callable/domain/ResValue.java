package com.ys.callable.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResValue<T> {
    private String msg;
    private String code;
    private T data;

}
