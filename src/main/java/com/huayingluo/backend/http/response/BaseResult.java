package com.huayingluo.backend.http.response;

import java.io.Serializable;


public class BaseResult<T> implements Serializable {

    /**
     * 响应的code码
     */
    private int code = 200;

    /**
     * 响应的success布尔值
     */
    private boolean success;

    /**
     * 响应的message
     */
    private String msg;

    /**
     * 响应的业务数据
     */
    private T data;

    public BaseResult(int code, String msg, T data) {

        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult(int code, boolean success, String msg, T data) {

        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult(String msg,  boolean success) {
        this.msg = msg;
        this.success = success;
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

    public static BaseResult fail(String msg) {
        BaseResult baseResult = new BaseResult(msg, false);
        return baseResult;
    }

    public static BaseResult success() {
        BaseResult baseResult = new BaseResult("success", true);
        return baseResult;
    }
}