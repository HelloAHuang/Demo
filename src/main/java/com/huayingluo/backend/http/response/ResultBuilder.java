package com.huayingluo.backend.http.response;

import com.huayingluo.backend.http.constant.HttpCode;

public class ResultBuilder {


    //自定义message 失败信息

    public static <T> BaseResult<T> err(String message) {

        return new BaseResult<T>(HttpCode.SERVER_ERROR.code, false, message, null);

    }

    //自定义message 失败信息

    public static <T> BaseResult<T> err(String message, T data) {

        return new BaseResult<T>(HttpCode.SERVER_ERROR.code, false, message, data);

    }

    //自定义message 失败信息

    public static <T> BaseResult<T> ok(String message) {

        return new BaseResult<T>(HttpCode.OK.code, true, message, null);

    }

    //自定义message 成功

    public static <T> BaseResult<T> ok(String message, T data) {

        return new BaseResult<T>(HttpCode.OK.code, true, message, data);

    }

    //自定义code,msg 返回数据

    public static <T> BaseResult<T> render(int code, boolean success, String msg) {

        return new BaseResult<T>(code, success, msg, null);

    }

    //自定义code,msg,data 返回数据

    public static <T> BaseResult<T> render(int code, boolean success, String msg, T data) {

        return new BaseResult<T>(code, success, msg, data);

    }

}