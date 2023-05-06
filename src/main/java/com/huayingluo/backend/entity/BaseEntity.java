package com.huayingluo.backend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private Long id;

    public Long getId() {
        return id;
    }
}