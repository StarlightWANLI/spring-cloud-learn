package com.hhly.jpa.springdatajpa.controller;

import com.hhly.jpa.springdatajpa.mq.many.NeoSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 按顺序接受
 */
@Api(description="一对多队列接口")
@RestController
public class OneToManyController {

    @Autowired
    private NeoSender neoSender;


    // http://localhost:8081/oneToMany
    @ApiOperation(value = "一对多请求接口")
    @GetMapping(value = "/oneToMany")
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender.send(i);
        }
    }
}
