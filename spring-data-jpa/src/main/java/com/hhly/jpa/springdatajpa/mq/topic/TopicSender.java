package com.hhly.jpa.springdatajpa.mq.topic;

import com.hhly.jpa.springdatajpa.mq.ConfirmCallBackListener;
import com.hhly.jpa.springdatajpa.mq.ReturnCallBackListener;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class TopicSender {

/*    @Autowired
    private AmqpTemplate rabbitTemplate;*/

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    ConfirmCallBackListener confirmCallBackListener;
    @Autowired
    ReturnCallBackListener returnCallBackListener;

    /**
     *   首先是完全匹配，没有找到，才回去模糊匹配    topic.#
     */
    public void send() {
        String context = "hi, 向队列topic.1发送消息";
        System.out.println("Sender : " + context);

        /**
         * 第一个参数是：指定交换器exchange
         * 第二个参数是：指定  路由煎routing key   用来和队列queues中的 binding key匹配
         * 第三个参数 ：  传递消息的内容
         *
         */
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.1", context);
    }

    public void send1() {
        String context = "hi, 向队列topic.message发送消息" + Math.random();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.message", context);

        //由于rabbitTemplate是单例的，所有这里的设置ConfirmCallback也是单例才行
        this.rabbitTemplate.setConfirmCallback(confirmCallBackListener);

        this.rabbitTemplate.setReturnCallback(returnCallBackListener);
    }


    /**
     * 优先完全匹配，之后再模糊匹配
     * 有完全匹配的首先完全匹配，比如  routingkey为    topic.message  ，因为存在topic.message的队列，所以只和topic.message的队列匹配，不匹配topic.#
     *
     *而交换器topicExchange下，routingkey为    topic.messages或 topic.1，都只能和topic.#匹配，而topic.#绑定了2个队列
     */
    public void send2() {
        String context = "hi, 向topic.messages发送消息";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("topicExchange", "topic.messages", context);
    }

}
