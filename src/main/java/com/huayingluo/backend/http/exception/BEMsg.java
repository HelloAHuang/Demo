package com.huayingluo.backend.http.exception;

public enum BEMsg {

    user_login_name_exist("登录名已存在"),

    login_user_error("用户名不存在或密码错误"),

    vote_repeat("您已点赞过"),

    no_auth_error("您无权进行当前操作"),

    no_Sign_error("请登录后再进行当前操作"),

    ;


    public String message;


    BEMsg(String message) {

        this.message = message;

    }


    public String getMessage() {

        return message;

    }


    public void setMessage(String message) {

        this.message = message;

    }

}