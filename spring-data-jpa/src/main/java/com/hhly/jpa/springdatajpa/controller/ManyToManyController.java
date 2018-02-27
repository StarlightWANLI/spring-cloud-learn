package com.hhly.jpa.springdatajpa.controller;

import com.hhly.jpa.springdatajpa.mq.many.NeoSender;
import com.hhly.jpa.springdatajpa.mq.many.NeoSender2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 按顺序接收
 */
@Api(description="对对多队列接口")
@RestController
public class ManyToManyController {



    @Autowired
    private NeoSender neoSender;

    @Autowired
    private NeoSender2 neoSender2;

    // http://localhost:8081/manyToMany

    @ApiOperation(value = "多对多请求接口")
    @GetMapping(value = "/manyToMany")
    public void manyToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender.send(i);
            neoSender2.send(i);
        }
    }
}
