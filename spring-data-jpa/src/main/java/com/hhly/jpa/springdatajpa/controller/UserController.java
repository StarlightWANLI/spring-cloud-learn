package com.hhly.jpa.springdatajpa.controller;


import com.google.common.base.Preconditions;
import com.hhly.jpa.springdatajpa.annatation.RequestLogging;
import com.hhly.jpa.springdatajpa.entity.Order;
import com.hhly.jpa.springdatajpa.entity.User;
import com.hhly.jpa.springdatajpa.model.request.OrderRequest;
import com.hhly.jpa.springdatajpa.model.request.PaymentRequest;
import com.hhly.jpa.springdatajpa.model.request.UserRequest;
import com.hhly.jpa.springdatajpa.model.response.ObjectDataResponse;
import com.hhly.jpa.springdatajpa.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 使用produces和consumes现在消费和返回的都是json格式的字符串
 */
@RestController
@RequestMapping(value = "/api/v1/user/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class UserController {
    @Autowired
    private OrderService orderService;


    /**
     * 控制层尽可能简单，只是提供请求的接受入口，业务逻辑尽量在服务层完成
     *
     * @Valid是用来校验实体类中指定属性的校验规则
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    @RequestLogging
    public ObjectDataResponse<User> query(@Valid @RequestBody UserRequest request) {
        return null;
    }


}
