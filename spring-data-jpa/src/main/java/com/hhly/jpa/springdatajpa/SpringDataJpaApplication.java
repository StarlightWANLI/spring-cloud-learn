package com.hhly.jpa.springdatajpa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.hhly.jpa.springdatajpa.repositories")
@EntityScan(basePackages = {"com.hhly.jpa.springdatajpa.domain"})
@MapperScan(basePackages = {"com.hhly.jpa.springdatajpa.mapper"})
@ServletComponentScan(basePackages = {"com.hhly.jpa.springdatajpa.listener","com.hhly.jpa.springdatajpa.filter"})
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}
}
