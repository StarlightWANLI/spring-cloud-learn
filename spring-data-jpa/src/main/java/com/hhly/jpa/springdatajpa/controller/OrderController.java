package com.hhly.jpa.springdatajpa.controller;


import com.hhly.jpa.springdatajpa.annatation.RequestLogging;
import com.hhly.jpa.springdatajpa.domain.Order;
import com.hhly.jpa.springdatajpa.model.request.OrderRequest;
import com.hhly.jpa.springdatajpa.model.request.PaymentRequest;
import com.hhly.jpa.springdatajpa.model.response.ObjectCollectionResponse;
import com.hhly.jpa.springdatajpa.model.response.ObjectDataResponse;
import com.hhly.jpa.springdatajpa.model.response.RestfulResponse;
import com.hhly.jpa.springdatajpa.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户订单控制层
 *
 * 请求改为标准的restful风格的请求
 *
 *这个限制的太死，看情况添加
 * produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}
 */
@RestController
@RequestMapping(value = "/api/v1")
@Api(description="订单操作接口")
public class OrderController {

     @Autowired
     private OrderService  orderService;


    /**
     * get请求不能传递RequestBody参数           restful风格，不一定真正适用，但是可以部分尝试
     * @param request
     * @return
     */
    @ApiOperation(value = "查询用户订单", notes = "根据用户id查询用户下的订单")
    @RequestMapping(value = "/orders/query", method = RequestMethod.POST)
    @RequestLogging
    public ObjectCollectionResponse<Order> query(@Valid @RequestBody OrderRequest request) {
        Order order = new Order();
        BeanUtils.copyProperties(request,order);
        //服务层提供通用的查询
        return orderService.query(order);
    }


    /**
     * get请求不能传递RequestBody参数           restful风格，不一定真正适用，但是可以部分尝试
     * @return
     */
    @ApiOperation(value = "查询用户订单", notes = "根据用户id查询用户下的订单")
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    @RequestLogging
    public ObjectCollectionResponse<Order> query( @PathVariable Long id) {
        Order order = new Order();
        order.setId(id);
        return orderService.query(order);
    }



    @ApiOperation(value = "生成或修改订单", notes = "生成或修改订单")
    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @RequestLogging
    public ObjectDataResponse<Order> save(@Valid @RequestBody Order order) {
        return orderService.save(order);
    }


    /**
     * delelte请求中，不能使用PathVariable传递参数,   是Get方法独有的
     * @param id
     * @return
     */
    @ApiOperation(value = "删除订单", notes = "删除订单")
    @RequestMapping(value = "/orders", method = RequestMethod.DELETE)
    @RequestLogging
    public RestfulResponse delete(@RequestParam Long id) {
        return orderService.delete(id);
    }


    /**
     * delelte请求中，不能使用PathVariable传递参数,   是Get方法独有的
     * @param id
     * @return
     */
    @ApiOperation(value = "使用路径参数删除订单", notes = "删除订单")
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    @RequestLogging
    public RestfulResponse deletePath(@PathVariable Long id) {
        return orderService.delete(id);
    }
}
