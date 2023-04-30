package com.huayingluo.backend.http.response;

import java.io.Serializable;


public class Result<T> implements Serializable {

    /**响应的code码*/

    private int code;

    /**响应的success布尔值*/

    private boolean success;

    /**响应的message*/

    private String msg;

    /**响应的业务数据*/

    private T data;



    public Result(int code, String msg, T data){

        this.code=code;

        this.msg=msg;

        this.data=data;

    }



    public Result(int code, boolean success, String msg, T data) {

        this.code = code;

        this.success = success;

        this.msg = msg;

        this.data = data;

    }



    public int getCode() {

        return code;

    }



    public void setCode(int code) {

        this.code = code;

    }



    public String getMsg() {

        return msg;

    }



    public void setMsg(String msg) {

        this.msg = msg;

    }



    public T getData() {

        return data;

    }



    public void setData(T data) {

        this.data = data;

    }



    public boolean isSuccess() {

        return success;

    }



    public void setSuccess(boolean success) {

        this.success = success;

    }

}