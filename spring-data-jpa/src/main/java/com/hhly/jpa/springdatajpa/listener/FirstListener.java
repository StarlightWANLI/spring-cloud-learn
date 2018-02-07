package com.hhly.jpa.springdatajpa.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听servlet容器的创建
 * 1、代码注册方式   通过ListenerConfig注册的监听器，不用和 @ServletComponentScan配合使用
 * 2、注解方式
 *     在监听器类上配置@WebListener，在启动类SpringDataJpaApplication上使用@ServletComponentScan指定扫描路径
 */
@Slf4j
//@WebListener
public class FirstListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("测试通过代码注册监听器FirstListener   创建……");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("测试通过代码注册监听器FirstListener   销毁...");
    }
}
