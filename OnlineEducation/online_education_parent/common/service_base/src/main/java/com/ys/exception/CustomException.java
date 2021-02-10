package com.ys.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private String msg;
    @ApiModelProperty(value = "状态码")
    private Integer code;
}
