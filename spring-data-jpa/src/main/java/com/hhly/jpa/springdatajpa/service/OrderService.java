package com.hhly.jpa.springdatajpa.service;


import com.hhly.jpa.springdatajpa.domain.Order;
import com.hhly.jpa.springdatajpa.model.request.OrderRequest;
import com.hhly.jpa.springdatajpa.model.response.ObjectCollectionResponse;
import com.hhly.jpa.springdatajpa.model.response.ObjectDataResponse;
import com.hhly.jpa.springdatajpa.model.response.RestfulResponse;

/**
 * @author Zhao Junjian
 */
public interface OrderService{

    ObjectCollectionResponse<Order> query(OrderRequest request);

    ObjectDataResponse<Order> save(Order order);

    RestfulResponse delete(Long id);
}
