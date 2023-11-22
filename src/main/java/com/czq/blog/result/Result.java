package com.czq.blog.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {
    private boolean success;

    private Integer code;

    private String msg;

    private Object data;

    public static Result success(Object data) {
        return new Result(true,200,"success",data);
    }
    public static Result fail(Integer code, String msg) {
        return new Result(false,code,msg,null);
    }
}
