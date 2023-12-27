package com.bs.util;

public enum ResultCode {
    SUCCESS(0,"成功"),
    ERROR(-1,"失败"),
    INFOR(1,"提示");

    private Integer code;
    private String message;

    private ResultCode(Integer code,String message) {
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
