package com.huayingluo.backend.http.response;

public enum ResponseStateEnum {

    success("success"),

    error("error");

    public String value;


    ResponseStateEnum(String state) {

        this.value = state;

    }


    ResponseStateEnum() {

    }


    public String getValue() {

        return value;

    }


    public void setValue(String value) {

        this.value = value;

    }

}