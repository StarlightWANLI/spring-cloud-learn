package com.hhly.jpa.springdatajpa.config;


import com.hhly.jpa.springdatajpa.listener.FirstListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 *  监听器的配置类
 *
 *  spring boot中的配置，监听器（Listener）的注册方法和 Servlet 一样，有两种方式：代码注册或者注解注册
 *  1、代码注册方式
 *  2、注解方式
 *
 *
 */
@Configuration
public class ListenerConfig {
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new FirstListener());
        return servletListenerRegistrationBean;
    }




}
