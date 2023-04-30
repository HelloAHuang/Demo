package com.huayingluo.backend.http.exception;

import com.huayingluo.backend.http.constant.HttpCode;

public class BusinessException extends BaseException {

    private int code = HttpCode.SERVER_ERROR.code;

    private String message;

    public BusinessException() {

    }


    public BusinessException(int code, String message) {

        super(code, message);

        this.code = code;

        this.message = message;

    }



    public BusinessException(Throwable ex) {

        super(ex);

    }



    public BusinessException(String message) {

        this(HttpCode.SERVER_ERROR.code, message);

    }



    public BusinessException(String msg, Throwable ex) {

        super(msg, ex);

    }



    @Override

    public int getCode() {

        return code;

    }



    @Override

    public void setCode(int code) {

        this.code = code;

    }



    @Override

    public String getMessage() {

        return message;

    }



    public void setMessage(String message) {

        this.message = message;

    }

}