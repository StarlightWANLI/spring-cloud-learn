package com.hhly.jpa.springdatajpa.commandLineRunner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 *  CommandLineRunner用来项目启动的时候，按顺序加载指定数据
 *  Order  加载顺序
 */
@Slf4j
@Component
@Order(value=1)
public class StartupLogging1Runner implements CommandLineRunner{
    @Override
    public void run(String... strings) throws Exception {
         log.info("startup  1  ……");
    }
}
