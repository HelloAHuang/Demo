package com.huayingluo.backend.controller;

import com.huayingluo.backend.entity.UserEntity;
import com.huayingluo.backend.http.exception.BaseException;
import com.huayingluo.backend.http.response.BaseResult;
import com.huayingluo.backend.http.response.ResultBuilder;
import com.huayingluo.backend.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyue
 * @version 1.0.0
 * @ClassName FirstController.java
 * @Description TODO
 * @createTime 2023年04月29日 19:29:00
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/test")
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     *
     */
    @GetMapping("")
    public String getTest() {
        return demoService.getTest();
    }

    /**
     * 隐式响应，即方法响应体就是业务实体，优点直观明了
     */
    @GetMapping("/getUser")
    public UserEntity getUser() {
        UserEntity userEntity = new UserEntity(1, "IT学习道场", 18);
        return userEntity;
    }


    /**
     * 显式响应，即方法响应体就是result，
     * <p>
     * 优点可以自定义一些定制化的响应数据组装
     * <p>
     * 但是需要自己构建Result对象
     */
    @GetMapping("/getResult")
    public BaseResult<UserEntity> getResult() {

        UserEntity userEntity = new UserEntity(1, "IT学习道场", 18);

        //可以根据不同的业务处理渲染不同的Result，增加了灵活性

        BaseResult<UserEntity> result = ResultBuilder.render(200, true, "只是我自己构建的Result", userEntity);

        return result;

    }


    /**
     * 测试统一异常处理（自定义异常）
     */

    @GetMapping("/testBaseException")

    public UserEntity testBaseException() {

        throw new BaseException(508, "触发自定义业务异常了");

    }


    /**
     * 测试统一异常处理（非自定义异常）
     */

    @GetMapping("/testException")

    public UserEntity testException() {

        throw new RuntimeException("业务异常了");

    }


}
