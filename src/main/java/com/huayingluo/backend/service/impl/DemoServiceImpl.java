package com.huayingluo.backend.service.impl;

import com.huayingluo.backend.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author huangyue
 * @version 1.0.0
 * @ClassName FirstServiceImpl.java
 * @Description TODO
 * @createTime 2023年04月29日 19:30:00
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String getTest() {
        return "hello world!";
    }
}
