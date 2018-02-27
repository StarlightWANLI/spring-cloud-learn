package com.hhly.jpa.springdatajpa.aspect;


import com.hhly.jpa.springdatajpa.config.RequestAttributeConst;
import com.hhly.jpa.springdatajpa.model.RequestDetailsLogger;
import com.hhly.jpa.springdatajpa.model.ResponseDetailsLogger;
import com.hhly.jpa.springdatajpa.util.ServletContextHolder;
import com.hhly.jpa.springdatajpa.annatation.RequestLogging;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.OffsetDateTime;

/**
 * 本类设计为当有被@RequestBodyLogs修饰的@ControllerAdvice或者@Controller抛出异常时记录输入输出,
 * 其他情况仅记录被标记的@RequestMapping或@ResponseBody方法
 *
 * @author Zhao Junjian
 * @see RequestLogging
 * @see org.springframework.web.bind.annotation.ControllerAdvice
 */
@Aspect
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Slf4j
@Component
public class RequestLoggingAspect{

    @Around(value = "within(com.hhly.jpa..*) " +
            "&& (@annotation(org.springframework.web.bind.annotation.ResponseBody)" +
            "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)) " +
            "&& @annotation(com.hhly.jpa.springdatajpa.annatation.RequestLogging)")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // 生成请求日志
        RequestDetailsLogger requestLog = generateJsonRequestDetails();
        // 获取Swagger上的API描述
        injectApiOperationDescription(joinPoint, requestLog);
        // 执行真实请求
        final Object proceed = joinPoint.proceed();
        // 当响应完成时, 打印完整的'request & response'信息
        requestLog.setResponseTime(OffsetDateTime.now());
        log.info("RequestLoggingAspect#\r\nREQUEST->\r\n{}\r\nRESPONSE->\r\n {}", requestLog, ResponseDetailsLogger.with(proceed));
        // 放行
        return proceed;
    }

    /**
     * 创建通用的日志输出模式并绑定线程
     *
     * @return 日志模型
     */
    private RequestDetailsLogger generateJsonRequestDetails() {
        RequestDetailsLogger logDetails = (RequestDetailsLogger) ServletContextHolder.getRequest().getAttribute(RequestAttributeConst.DETAILS_KEY);
        if (logDetails == null) {
            logDetails = new RequestDetailsLogger();
            ServletContextHolder.getRequest().setAttribute(RequestAttributeConst.DETAILS_KEY, logDetails);
        }
        return logDetails;
    }

    private void injectApiOperationDescription(ProceedingJoinPoint joinPoint, RequestDetailsLogger logDetails) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        final ApiOperation operate = method.getAnnotation(ApiOperation.class);
        if (operate != null) {
            logDetails.setApiDesc(operate.value());
        }
    }

}
