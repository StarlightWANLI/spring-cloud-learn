package com.hhly.jpa.springdatajpa.mapper;


import com.hhly.jpa.springdatajpa.domain.Order;

import java.util.List;

public interface OrderMapper{

    public List<Order> findAll(Order order);

}