package com.hhly.jpa.springdatajpa.mq.hello;

import com.hhly.jpa.springdatajpa.annatation.RequestLogging;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {


    //使用@RabbitListener监听指定队列、指定exchange、指定routingKey的消息
    //同时@RabbitListener有建立队列、exchange、routingKey的功能
/*    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "editCatalog" , durable = "true") ,
                    exchange = @Exchange(value = "catalogExchange" , type = "topic" , durable = "true") ,
                    key = "editCatalogKey")
    )*/
    @RequestLogging
    @RabbitHandler
    public void process(String hello) {
        System.out.println("单对单接收参数  : " + hello);
    }
}
