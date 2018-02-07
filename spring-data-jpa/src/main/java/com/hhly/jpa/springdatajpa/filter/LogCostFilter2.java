package com.hhly.jpa.springdatajpa.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 通过过滤器来实现记录请求执行时间的功能
 * /**
 * @WebFilter这个注解并没有指定执行顺序的属性，其执行顺序依赖于Filter的名称，是根据Filter类名（注意不是配置的filter的名字）
 * 的字母顺序倒序排列，并且@WebFilter指定的过滤器优先级都高于FilterRegistrationBean配置的过滤器。
 */
@Slf4j
//@WebFilter(urlPatterns = "/*", filterName = "LogFilter2")
public class LogCostFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
           log.info("初始化过滤器  LogFilter2 ……");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("LogFilter2  Execute cost="+(System.currentTimeMillis()-start));
    }

    @Override
    public void destroy() {
        log.info("销毁过滤器  LogFilter2 ……");
    }
}
