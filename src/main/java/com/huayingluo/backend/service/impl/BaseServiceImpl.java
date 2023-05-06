package com.huayingluo.backend.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huayingluo.backend.entity.BaseEntity;
import com.huayingluo.backend.entity.EntityValidator;
import com.huayingluo.backend.http.response.BaseResult;
import com.huayingluo.backend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseServiceImpl<T extends BaseEntity, D extends BaseMapper<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    @Override
    public BaseResult create(T model) {
        BaseResult result = EntityValidator.validate(model);
        if (!result.isSuccess()) {
            return result;
        }
        return getBaseResult(dao.insert(model), "新增失败");
    }

    public T getById(int id) {
        return dao.selectById(id);
    }

    public BaseResult update(T model) {
        BaseResult result = EntityValidator.validate(model);
        if (!result.isSuccess()) {
            return result;
        }
        return getBaseResult(dao.updateById(model), "更新失败");
    }

    public BaseResult delete(int id) {
        return getBaseResult(dao.deleteById(id), "删除失败");
    }

    private BaseResult getBaseResult(int row, String error) {
        BaseResult result = BaseResult.success();

        if (row <= 0) {
            result = BaseResult.fail(error);
        }
        return result;
    }
}