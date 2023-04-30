package com.huayingluo.backend.http.response;

public class ResultException {

    /**
     * 响应码
     */

    private int code;

    private boolean success = false;

    /**
     * 响应消息提醒
     */

    private String msg;

    /**
     * 请求的异常路径
     */

    private String url;


    public ResultException(int code, String msg, String url) {

        this.code = code;

        this.msg = msg;

        this.url = url;

    }


    public ResultException(int code, boolean success, String msg, String url) {

        this.code = code;

        this.success = success;

        this.msg = msg;

        this.url = url;

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


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


    public boolean isSuccess() {

        return success;

    }


    public void setSuccess(boolean success) {

        this.success = success;

    }

}
