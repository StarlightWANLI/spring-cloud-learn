package com.hhly.jpa.springdatajpa.config;

import com.hhly.jpa.springdatajpa.model.SwaggerApiInfo;
import com.hhly.jpa.springdatajpa.model.type.StatusCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hhly.jpa.springdatajpa.controller"))//直接指定到控制层
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring集成swagger")
                .description("我的博客：http://blog.csdn.net/qq_16814591/article/details/52223687")
                .termsOfServiceUrl("http://blog.csdn.net/qq_16814591/article/details/52223687")
                .contact(new Contact("wanli","http://blog.csdn.net/w1014074794","1014074794@qq.com"))
                .version("1.0")
                .build();
    }
}
