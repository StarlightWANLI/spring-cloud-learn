package com.hhly.jpa.springdatajpa.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *  rabbitmq中，有些配置在项目启动生效后，再注释代码重启项目，配置可能仍然有效
 */
@Configuration
public class RabbitConfig {


    /**
     * 这里一定要通过配置文件来指定队列，不然项目启动的时候会出现找不到对应队列的异常
     *
     * 接受者也会接受不到队列中的信息
     *
     * 默认情况下，使用direct分发策略匹配
     * 直接使用队列名称作为binding key，和消息中的routing key匹配
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue neoQueue() {
        return new Queue("neo");
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object");
    }
}