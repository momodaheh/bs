package com.bs.util;

import lombok.Data;

@Data
public class ResultEntity<T> {
    public static <T> ResultEntity<T> infor(ResultCode rc) {
        return new ResultEntity<T>(rc);
    }

    public static <T> ResultEntity<T> infor(ResultCode rc,T data ) {
        return new ResultEntity<T>(rc,data);
    }

    private ResultEntity(ResultCode rc) {
        this.code=rc.getCode();
        this.msg=rc.getMessage();
    }

    private ResultEntity(ResultCode rc ,T data) {
        this.code=rc.getCode();
        this.msg=rc.getMessage();
        this.data=data;
    }


    private Integer code;
    private String msg;
    private T data;
}
