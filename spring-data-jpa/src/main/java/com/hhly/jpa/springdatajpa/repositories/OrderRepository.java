package com.hhly.jpa.springdatajpa.repositories;

import com.hhly.jpa.springdatajpa.domain.Order;

import java.util.List;

public interface OrderRepository extends  MyBaseRepository<Order, Long>{

    List<Order> findByUserId(Long userId);
}
