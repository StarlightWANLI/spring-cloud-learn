package com.hhly.jpa.springdatajpa.controller;

import com.hhly.jpa.springdatajpa.domain.User;
import com.hhly.jpa.springdatajpa.mq.object.ObjectSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ObjectController {



    @Autowired
    private ObjectSender sender;

    //http://localhost:8081/object

    @GetMapping(value = "/object")
    public void sendOject() throws Exception {
        User user=new User();
        user.setUserName("wanli");
        user.setLoginPwd("123456");
        sender.send(user);
    }
}

