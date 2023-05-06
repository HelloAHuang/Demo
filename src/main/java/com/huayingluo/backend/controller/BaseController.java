package com.huayingluo.backend.controller;

import com.huayingluo.backend.entity.BaseEntity;
import com.huayingluo.backend.http.response.BaseResult;
import com.huayingluo.backend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class BaseController<T extends BaseEntity, S extends BaseService<T>> {

    @Autowired
    protected S service;

    @ModelAttribute
    public T getModel(Integer id) {
        T model = initFormEntity();
        if (id != null && id > 0) {
            model = service.getById(id);
        }

        return model;
    }

    @RequestMapping("/list")
    public String list(Model model) {

        return getJspPath() + "/list";
    }

    @RequestMapping(value = {"/create", "edit"}, method = RequestMethod.GET)
    public String form() {

        return getJspPath() + "/form";
    }

    @RequestMapping("/detail")
    public String detail() {
        return getJspPath() + "/detail";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public BaseResult delete(int id) {
        return service.delete(id);
    }

    @RequestMapping(value = {"/create", "edit"}, method = RequestMethod.POST)
    public String form(T entity, Model model) {
        BaseResult result;
        if (entity.getId() != null && entity.getId() > 0) {
            result = service.update(entity);
        } else {
            result = service.create(entity);
        }

        //新增或者修改成功返回列表页
        if (result.isSuccess()) {

            return "redirect:list";
        }

        //失败显示错误信息
        model.addAttribute("result", result);
        return getJspPath() + "/form";
    }

    protected abstract T initFormEntity();

    protected String getJspPath() {
        return getClass().getSimpleName().toLowerCase().replace("controller", "");
    }
}