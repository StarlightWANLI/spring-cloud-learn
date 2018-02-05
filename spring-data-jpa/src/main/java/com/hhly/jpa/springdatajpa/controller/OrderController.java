package com.hhly.jpa.springdatajpa.controller;


import com.hhly.jpa.springdatajpa.annatation.RequestLogging;
import com.hhly.jpa.springdatajpa.entity.Order;
import com.hhly.jpa.springdatajpa.entity.User;
import com.hhly.jpa.springdatajpa.model.request.PaymentRequest;
import com.hhly.jpa.springdatajpa.model.response.ObjectDataResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户订单控制层
 */
@RestController
@RequestMapping(value = "/api/v1/order", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class OrderController {


    @ApiOperation(value = "查询用户订单", notes = "根据用户id查询用户下的订单")
    @RequestMapping(value = "/queyr", method = RequestMethod.POST)
    @RequestLogging
    public ObjectDataResponse<Order> query(@Valid @RequestBody PaymentRequest request, BindingResult result) {
        return new ObjectDataResponse();
    }



}
