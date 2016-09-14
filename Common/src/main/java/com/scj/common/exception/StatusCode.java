package com.scj.common.exception;

/**
 * Created by Administrator on 2016/7/11.
 */
public enum StatusCode {

    USER_NOT_EXISTED(1,"该用户不存在"),
    USER_REGISTERED_ALREADY(2,"用户已注册"),
    USERNAME_PASSWORD_WRONG(3,"用户名和密码错误"),

    NOTE_TAG_NOT_EXISTED(4,"该标签不存在")
    ;

    private  int code;
    private  String message;

    StatusCode(int code,String message)
    {
        this.code=code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
