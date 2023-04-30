package com.huayingluo.backend.http.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BaseException extends RuntimeException {

    private int code;

    private String message;

    public BaseException() {

    }

    public BaseException(int code, String message) {

        super(message);

        this.code = code;

        this.message = message;

    }

    public BaseException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

        super(msg, cause, enableSuppression, writableStackTrace);

    }


    public BaseException(String message, Throwable cause) {

        super(message, cause);

    }


    public BaseException(String message) {

        super(message);

    }


    public BaseException(Throwable cause) {

        super(cause);

    }


    public static String getStackTrace(Throwable throwable) {

        StringWriter sw = new StringWriter();

        PrintWriter pw = new PrintWriter(sw, true);

        throwable.printStackTrace(pw);

        return sw.getBuffer().toString();

    }


    public int getCode() {

        return code;

    }


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


    @Override

    public String toString() {

        return "BaseException{" +

                "code=" + code +

                ", msg='" + message + '\'' +

                '}';

    }

}