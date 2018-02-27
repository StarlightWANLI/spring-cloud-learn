package com.hhly.jpa.springdatajpa.controller;

import com.hhly.jpa.springdatajpa.mq.topic.TopicSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * topic 交换器通过模式匹配分配消息的路由键属性，将路由键和某个模式进行匹配，此时队列需要绑定到一个模式上。
 * 它将路由键和绑定键的字符串切分成单词，这些单词之间用点隔开。它同样也会识别两个通配符：符号“#”和符号“”。
 */
@Api("Topic路由匹配模式接口")
@RestController
public class TopicController {


    @Autowired
    private TopicSender sender;

    //http://localhost:8081/topic
    @ApiOperation(value = "向topic主题发送消息")
    @GetMapping(value = "/topic")
    public void topic() throws Exception {
        sender.send();
    }

    //http://localhost:8081/topic1
    @ApiOperation(value = "向topic1主题发送消息")
    @GetMapping(value = "/topic1")
    public void topic1() throws Exception {
        sender.send1();
    }

    //http://localhost:8081/topic1
    @ApiOperation(value = "向topic2主题发送消息")
    @GetMapping(value = "/topic2")
    public void topic2() throws Exception {
        sender.send2();
    }
}
