package com.hhly.jpa.springdatajpa.repositories;

import com.hhly.jpa.springdatajpa.entity.Order;
import com.hhly.jpa.springdatajpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository extends  MyBaseRepository<Order, Long>{

}
