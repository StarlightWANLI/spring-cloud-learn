package com.springboot.oauth.authorize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 资源认证服务
 *
 *
 * 在启动类中添加@EnableAuthorizationServer注解,启用认证服务
 *
 */
@SpringBootApplication
public class AuthorizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizeApplication.class, args);
	}
}
