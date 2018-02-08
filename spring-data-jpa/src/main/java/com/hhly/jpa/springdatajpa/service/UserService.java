package com.hhly.jpa.springdatajpa.service;


import com.hhly.jpa.springdatajpa.domain.Order;
import com.hhly.jpa.springdatajpa.domain.User;
import com.hhly.jpa.springdatajpa.model.ResultMsg;
import com.hhly.jpa.springdatajpa.model.response.ObjectCollectionResponse;
import com.hhly.jpa.springdatajpa.model.response.ObjectDataResponse;
import com.hhly.jpa.springdatajpa.model.response.RestfulResponse;

import java.util.List;

/**
 * @author Zhao Junjian
 */
public interface UserService {

    List<User> query(User user);

    User save(User user);

    void delete(Long id);
}
