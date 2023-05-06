package com.huayingluo.backend.controller;

import com.huayingluo.backend.entity.PageQuery;
import com.huayingluo.backend.entity.PageResult;
import com.huayingluo.backend.entity.TbUserEntity;
import com.huayingluo.backend.entity.dto.TbUserQuery;
import com.huayingluo.backend.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyue
 * @version 1.0.0
 * @ClassName FirstController.java
 * @Description TODO
 * @createTime 2023年04月29日 19:29:00
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<TbUserEntity, UserService> {

    @ResponseBody
    @RequestMapping("/page")
    public PageResult<TbUserEntity> page(PageQuery<TbUserQuery> pageQuery, TbUserQuery query) {
        pageQuery.setQuery(query);
        PageResult<TbUserEntity> tbUsers = service.getByPage(pageQuery);
        return tbUsers;
    }

    @Override
    protected TbUserEntity initFormEntity() {
        return new TbUserEntity();
    }
}
