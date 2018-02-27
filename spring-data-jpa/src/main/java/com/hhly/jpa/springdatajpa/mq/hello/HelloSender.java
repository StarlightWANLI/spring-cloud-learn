package com.hhly.jpa.springdatajpa.mq.hello;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public String  send() {
        String context = "hello " + "你好我是单对单测试";
        log.info("单对单发送参数 : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
        return "发送成功";
    }
}
