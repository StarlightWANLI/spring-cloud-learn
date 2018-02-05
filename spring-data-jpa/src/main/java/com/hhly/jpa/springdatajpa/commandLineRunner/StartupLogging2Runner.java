package com.hhly.jpa.springdatajpa.commandLineRunner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 *  CommandLineRunner用来项目启动的时候，按顺序加载指定数据
 *  Order  加载顺序
 */
@Slf4j
@Component
@Order(value=2)
public class StartupLogging2Runner implements CommandLineRunner{
    @Value("${spring.datasource.password}")
    private String password;

    @Value("${test.password}")
    private String key;

    @Override
    public void run(String... strings) throws Exception {
         log.info("startup  2  ……"  + password);
        log.info("startup  2   密码值："  + key);
    }
}
