package com.hhly.jpa.springdatajpa.controller;

import com.hhly.jpa.springdatajpa.mq.hello.HelloSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description="单对单队列接口")
@RestController
public class HelloController {
    @Autowired
    private HelloSender helloSender;

    // http://localhost:8081/hello
    @ApiOperation(value = "单对单接口测试", notes = "")
    @GetMapping(value = "/hello")
    public void hello() throws Exception {
        helloSender.send();
    }
}
