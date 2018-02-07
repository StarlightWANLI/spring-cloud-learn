package com.hhly.jpa.springdatajpa.interceptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  拦截器，记录请求时间
 *
 *  拦截器只能通过config配置类来注入，没有对应的注解注入方法
 *
 *  因为preHandle和postHandle是两个方法，所以我们这里不得不设置一个共享变量start来存储开始值，但是这样就会存在线程安全问题。
 *  当然，我们可以通过其他方法来解决，比如通过ThreadLocal就可以很好的解决这个问题，有兴趣的同学可以自己实现。不过通过这一点我们其实可以看到，
 *  虽然拦截器在很多场景下优于过滤器，但是在这种场景下，过滤器比拦截器实现起来更简单。
 *
 *
 *  拦截器的作用：
 *  这里的拦截器只有经过DispatcherServlet 的请求，才会走拦截器链，默认不拦截静态资源，Spring Boot中默认的静态资源路径有
 *  classpath:/META-INF/resources/，classpath:/resources/，classpath:/static/，classpath:/public/ ，
 *  在拦截器中我们可以处理一些我们需要的业务，比如防xss攻击，在调用controller前对提交内容进行过滤等等。
 */
@Slf4j
public class LogCostInterceptor implements HandlerInterceptor {

    long start = System.currentTimeMillis();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //controller方法调用之前
        start = System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // //请求处理之后进行调用，但是在视图被渲染之前，即Controller方法调用之后
        log.info("Interceptor cost="+(System.currentTimeMillis()-start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行，主要是用于进行资源清理工作
    }
}
