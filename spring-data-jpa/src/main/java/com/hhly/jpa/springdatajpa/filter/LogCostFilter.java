package com.hhly.jpa.springdatajpa.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 通过过滤器来实现记录请求执行时间的功能
 */
@Slf4j
public class LogCostFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
           log.info("初始化过滤器  LogCostFilter ……");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestURI = req.getRequestURI();
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("请求名称：" + requestURI+ "，请求花费时间 Execute cost="+(System.currentTimeMillis()-start));
    }

    @Override
    public void destroy() {
        log.info("销毁过滤器  LogCostFilter ……");
    }
}
