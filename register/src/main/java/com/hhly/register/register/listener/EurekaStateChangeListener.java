package com.hhly.register.register.listener;

import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * Eureka的server端会发出5个事件通知，分别是：
 EurekaInstanceCanceledEvent             服务下线事件
 EurekaInstanceRegisteredEvent           服务注册事件
 EurekaInstanceRenewedEvent              服务续约事件
 EurekaRegistryAvailableEvent            Eureka注册中心启动事件
 EurekaServerStartedEvent                EurekaServer启动事件
 */
@Slf4j
@Component
public class EurekaStateChangeListener {
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        //服务断线事件
        log.info("服务:{}挂了",event.getAppName());
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        log.info("服务：{}，注册成功了",event.getInstanceInfo().getAppName());
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        event.getAppName();
        event.getServerId();
        log.info("心跳检测服务：{}",event.getAppName());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        log.info("Eureka注册中心启动事件",event.getSource());
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        //Server启动
        log.info("Eureka Server启动事件");
    }
}
