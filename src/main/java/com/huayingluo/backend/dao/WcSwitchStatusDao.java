package com.huayingluo.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huayingluo.backend.entity.PageQuery;
import com.huayingluo.backend.entity.TbUserEntity;
import com.huayingluo.backend.entity.WcSwitchStatusEntity;
import com.huayingluo.backend.entity.dto.TbUserQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author huangyue
 * @version 1.0.0
 * @ClassName UserDao.java
 * @Description TODO
 * @createTime 2023年05月01日 22:07:00
 */
@Mapper
public interface WcSwitchStatusDao extends BaseMapper<WcSwitchStatusEntity> {
}
