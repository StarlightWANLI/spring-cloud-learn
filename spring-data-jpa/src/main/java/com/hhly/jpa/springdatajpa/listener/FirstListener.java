package com.hhly.jpa.springdatajpa.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听servlet容器的创建
 * 1、代码注册方式
 * 2、注解方式
 */
@Slf4j
//@WebListener
public class FirstListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("FirstListener 初始化...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("FirstListener 销毁...");
    }
}
