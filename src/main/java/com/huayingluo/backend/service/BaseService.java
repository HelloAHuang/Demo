package com.huayingluo.backend.service;

import com.huayingluo.backend.entity.BaseEntity;
import com.huayingluo.backend.http.response.BaseResult;

public interface BaseService<T extends BaseEntity> {

    BaseResult create(T model);

    T getById(int id);

    BaseResult update(T model);

    BaseResult delete(int id);

}