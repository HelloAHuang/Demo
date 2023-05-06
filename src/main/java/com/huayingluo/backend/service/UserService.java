package com.huayingluo.backend.service;

import com.huayingluo.backend.entity.PageQuery;
import com.huayingluo.backend.entity.PageResult;
import com.huayingluo.backend.entity.TbUserEntity;
import com.huayingluo.backend.entity.dto.TbUserQuery;

/**
 * @author huangyue
 * @version 1.0.0
 * @ClassName FirstService.java
 * @Description TODO
 * @createTime 2023年04月29日 19:30:00
 */
public interface UserService extends BaseService<TbUserEntity>{
    PageResult<TbUserEntity> getByPage(PageQuery<TbUserQuery> pageQuery);
    TbUserEntity login(String userName, String password);
}
