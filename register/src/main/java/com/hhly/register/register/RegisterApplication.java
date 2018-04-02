package com.hhly.register.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * /**
 * SpringCLoud中的“Discovery Service”有多种实现，比如：eureka, consul, zookeeper。
 1，@EnableDiscoveryClient注解是基于spring-cloud-commons依赖，并且在classpath中实现；
 2，@EnableEurekaClient注解是基于spring-cloud-netflix依赖，只能为eureka作用；

 如果你的classpath中添加了eureka，则它们的作用是一样的。
 如果使用consul, zookeeper作为注册中心，则必须使用@EnableDiscoveryClient注解
 使用zookeeper作为注册中心的例子：
 http://blog.csdn.net/mn960mn/article/details/51803703

 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}
}
