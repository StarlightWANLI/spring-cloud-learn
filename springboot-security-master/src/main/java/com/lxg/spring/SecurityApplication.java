package com.lxg.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by lxg
 * on 2017/2/18.
 */
@SpringBootApplication
@EnableEurekaClient
public class SecurityApplication {

    public static void main(String[] args){
        new SpringApplication(SecurityApplication.class).run(args);
    }
}
