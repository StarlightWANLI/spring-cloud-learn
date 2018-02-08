package com.hhly.jpa.springdatajpa.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hhly.jpa.springdatajpa.domain.User;
import com.hhly.jpa.springdatajpa.mapper.UserMapper;
import com.hhly.jpa.springdatajpa.repositories.UserRepository;
import com.hhly.jpa.springdatajpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> query(User user) {
        if (user.getPageSize() != null && user.getPageNum() != null) {
            PageHelper.startPage(user.getPageNum(),user.getPageSize());
        }
        return userMapper.findAll(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {

    }


}
