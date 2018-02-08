package com.hhly.jpa.springdatajpa.service.impl;


import com.google.common.base.Preconditions;
import com.hhly.jpa.springdatajpa.domain.Order;
import com.hhly.jpa.springdatajpa.mapper.OrderMapper;
import com.hhly.jpa.springdatajpa.model.request.OrderRequest;
import com.hhly.jpa.springdatajpa.model.response.ObjectCollectionResponse;
import com.hhly.jpa.springdatajpa.model.response.ObjectDataResponse;
import com.hhly.jpa.springdatajpa.model.response.RestfulResponse;
import com.hhly.jpa.springdatajpa.repositories.OrderRepository;
import com.hhly.jpa.springdatajpa.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单服务类
 */
@Slf4j
@Service
public class OrderSerciceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ObjectCollectionResponse<Order> query(Order order) {
      //  List<Order> orderList = orderRepository.findByUserId( request.getUserId());
        List<Order> orderList = orderMapper.findAll(order);
        return new ObjectCollectionResponse(orderList);
    }

    @Override
    public ObjectDataResponse<Order> save(Order order) {
        return new ObjectDataResponse(orderRepository.save(order));
    }

    @Override
    public RestfulResponse delete(Long id) {
        orderRepository.delete(id);
        return new RestfulResponse();
    }

}
