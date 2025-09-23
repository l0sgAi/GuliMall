package com.losgai.gulimall.common.common.exception;

import lombok.Getter;

@Getter
public enum BaseCodeEnume {
    VALID_EXCEPTION(10001,"参数格式不正确"),
    UNKNOWN_EXCEPTION(10000,"系统未知异常"),
    USER_EXIST_EXCEPTION(10005,"用户已存在"),
    USER_NOT_EXIST_EXCEPTION(10006,"用户不存在"),
    USER_PASSWORD_EXCEPTION(10007,"密码错误"),
    USER_NOT_LOGIN_EXCEPTION(10008,"用户未登录"),
    USER_LOGIN_EXCEPTION(10009,"用户已登录"),
    USER_NOT_PERMISSION_EXCEPTION(10010,"用户没有权限"),
    ES_PRODUCT_UP_EXCEPTION(11000,"商品上架ES失败"),
    ;

    private final int code;
    private final String msg;

    BaseCodeEnume(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
