package com.hhly.jpa.springdatajpa.config;


import com.hhly.jpa.springdatajpa.filter.LogCostFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @WebFilter这个注解并没有指定执行顺序的属性，其执行顺序依赖于Filter的名称，是根据Filter类名（注意不是配置的filter的名字）
 * 的字母顺序倒序排列，并且@WebFilter指定的过滤器优先级都高于FilterRegistrationBean配置的过滤器。
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogCostFilter());
        //registration.addUrlPatterns("/*");  ///api/v1
        registration.addUrlPatterns("/api/v1/*");
        registration.setName("LogCostFilter");
        //设置过滤器执行顺序
        registration.setOrder(1);
        return registration;
    }
}
