package com.hhly.jpa.springdatajpa.mapper;


import com.hhly.jpa.springdatajpa.domain.User;

import java.util.List;

public interface UserMapper {

    public List<User> findAll(User user);

}