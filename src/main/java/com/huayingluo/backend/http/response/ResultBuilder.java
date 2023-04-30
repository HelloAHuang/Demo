package com.huayingluo.backend.http.response;

import com.huayingluo.backend.http.constant.HttpCode;

public class ResultBuilder {


    //自定义message 失败信息

    public static <T> Result<T> err(String message) {

        return new Result<T>(HttpCode.SERVER_ERROR.code, false, message, null);

    }

    //自定义message 失败信息

    public static <T> Result<T> err(String message, T data) {

        return new Result<T>(HttpCode.SERVER_ERROR.code, false, message, data);

    }

    //自定义message 失败信息

    public static <T> Result<T> ok(String message) {

        return new Result<T>(HttpCode.OK.code, true, message, null);

    }

    //自定义message 成功

    public static <T> Result<T> ok(String message, T data) {

        return new Result<T>(HttpCode.OK.code, true, message, data);

    }

    //自定义code,msg 返回数据

    public static <T> Result<T> render(int code, boolean success, String msg) {

        return new Result<T>(code, success, msg, null);

    }

    //自定义code,msg,data 返回数据

    public static <T> Result<T> render(int code, boolean success, String msg, T data) {

        return new Result<T>(code, success, msg, data);

    }

}