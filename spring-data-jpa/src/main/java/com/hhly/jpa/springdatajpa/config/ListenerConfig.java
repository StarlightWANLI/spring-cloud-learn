package com.hhly.jpa.springdatajpa.config;


import com.hhly.jpa.springdatajpa.listener.FirstListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
 *  监听器的配置类
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
