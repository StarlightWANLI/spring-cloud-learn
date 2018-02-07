package com.hhly.jpa.springdatajpa.config;

import com.hhly.jpa.springdatajpa.interceptor.LogCostInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig  extends WebMvcConfigurerAdapter{

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogCostInterceptor()).addPathPatterns("/*");
        //registry.addInterceptor(new MyInterceptor_copy()).addPathPatterns("/*");//有多个拦截器继续add进去
        super.addInterceptors(registry);
    }

}
