package com.huayingluo.backend.controller;

import com.huayingluo.backend.entity.WcSwitchStatusEntity;
import com.huayingluo.backend.http.response.BaseResult;
import com.huayingluo.backend.service.WcSwitchStatusService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyue
 * @version 1.0.0
 * @ClassName InteractiveController.java
 * @Description 小程序与充电桩控制接口
 * @createTime 2023年04月29日 19:29:00
 */
@RestController
@RequestMapping("/interactive")
public class InteractiveController extends BaseController<WcSwitchStatusEntity, WcSwitchStatusService> {

    @ResponseBody
    @RequestMapping("/status")
    public WcSwitchStatusEntity getStatus() {
        WcSwitchStatusEntity switchStatusEntity = service.getById(1);
        return switchStatusEntity;
    }

    @ResponseBody
    @RequestMapping("/status/close")
    public BaseResult closeSwitch() {
        WcSwitchStatusEntity entity = new WcSwitchStatusEntity();
        entity.setIsClosed(1);
        entity.setId(1l);
        return service.update(entity);
    }

    @ResponseBody
    @RequestMapping("/status/open")
    public BaseResult openSwitch() {
        WcSwitchStatusEntity entity = new WcSwitchStatusEntity();
        entity.setIsClosed(0);
        entity.setId(1l);
        return service.update(entity);
    }

    @Override
    protected WcSwitchStatusEntity initFormEntity() {
        return new WcSwitchStatusEntity();
    }
}
