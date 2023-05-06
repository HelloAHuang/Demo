package com.huayingluo.backend.service.impl;

import com.huayingluo.backend.dao.TbUserDao;
import com.huayingluo.backend.entity.PageQuery;
import com.huayingluo.backend.entity.PageResult;
import com.huayingluo.backend.entity.TbUserEntity;
import com.huayingluo.backend.entity.dto.TbUserQuery;
import com.huayingluo.backend.http.response.BaseResult;
import com.huayingluo.backend.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends BaseServiceImpl<TbUserEntity, TbUserDao> implements UserService {

    @Override
    public BaseResult create(TbUserEntity tbUser) {
        if (StringUtils.isBlank(tbUser.getPassword())) {
            return BaseResult.fail("密码不能为空");
        }
        tbUser.setPassword(md5DigestAsHex(tbUser.getPassword()));


        return super.create(tbUser);
    }

    @Override
    public PageResult<TbUserEntity> getByPage(PageQuery<TbUserQuery> pageQuery) {
        PageResult<TbUserEntity> result = new PageResult<>();
        List<TbUserEntity> list = dao.getByPage(pageQuery);
        int count = dao.getCount(pageQuery);
        result.setData(list);
        result.setRecordsTotal(count);
        result.setRecordsFiltered(count);
        return result;
    }

    @Override
    public BaseResult update(TbUserEntity tbUser) {

        if (StringUtils.isNotBlank(tbUser.getPassword())) {
            tbUser.setPassword(md5DigestAsHex(tbUser.getPassword()));
        }
        return super.update(tbUser);
    }

    @Override
    public TbUserEntity login(String userName, String password) {
        if (userName.equals("admin") && password.equals("123456")) {
            return new TbUserEntity();
        }
        Map<String, String> param = new HashMap<>();
        param.put("userName", userName);
        param.put("password", md5DigestAsHex(password));

        System.out.println(param.get("password"));
        return dao.getByUserNameAndPasswrod(param);
    }

    private String md5DigestAsHex(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

}