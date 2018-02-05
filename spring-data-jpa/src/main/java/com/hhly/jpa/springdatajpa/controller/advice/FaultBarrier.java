package com.hhly.jpa.springdatajpa.controller.advice;


import com.google.common.collect.ImmutableMap;
import com.hhly.jpa.springdatajpa.annatation.RequestLogging;
import com.hhly.jpa.springdatajpa.config.RequestAttributeConst;
import com.hhly.jpa.springdatajpa.exception.IllegalValidateException;
import com.hhly.jpa.springdatajpa.exception.RestStatusException;
import com.hhly.jpa.springdatajpa.model.ErrorEntity;
import com.hhly.jpa.springdatajpa.model.type.RestStatus;
import com.hhly.jpa.springdatajpa.model.type.StatusCode;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class FaultBarrier {
    private static final Logger LOGGER = LoggerFactory.getLogger(FaultBarrier.class);
    private static final ImmutableMap<Class<? extends Throwable>, RestStatus> EXCEPTION_MAPPINGS;

    static {
        final ImmutableMap.Builder<Class<? extends Throwable>, RestStatus> builder = ImmutableMap.builder();
        // SpringMVC中参数类型转换异常，常见于String找不到对应的ENUM而抛出的异常
        builder.put(MethodArgumentTypeMismatchException.class, StatusCode.INVALID_PARAMS_CONVERSION);
        builder.put(UnsatisfiedServletRequestParameterException.class, StatusCode.INVALID_PARAMS_CONVERSION);
        // HTTP Request Method不存在
        builder.put(HttpRequestMethodNotSupportedException.class, StatusCode.REQUEST_METHOD_NOT_SUPPORTED);
        // 要求有RequestBody的地方却传入了NULL
        builder.put(HttpMessageNotReadableException.class, StatusCode.HTTP_MESSAGE_NOT_READABLE);
        // 通常是操作过快导致DuplicateKey
        builder.put(DuplicateKeyException.class, StatusCode.DUPLICATE_KEY);
        // 其他未被发现的异常
        builder.put(Exception.class, StatusCode.SERVER_UNKNOWN_ERROR);
        EXCEPTION_MAPPINGS = builder.build();
    }

    /**
     * <strong>Request域取出对应错误信息</strong>, 封装成实体ErrorEntity后转换成JSON输出
     *
     * @param e       {@code StatusCode}异常
     * @param request HttpServletRequest
     * @return ErrorEntity
     * @see ErrorEntity
     * @see StatusCode
     */
    @ResponseBody
    @RequestLogging
    @ExceptionHandler(RestStatusException.class)
    public Object restStatusException(Exception e, HttpServletRequest request) {
        // 取出存储在Shift设定在Request Scope中的ErrorEntity
        return request.getAttribute(e.getMessage());
    }

    /**
     * <strong>Request域取出对应错误信息</strong>, 封装成实体ErrorEntity后转换成JSON输出
     *
     * @param e       {@code IllegalValidateException}异常
     * @param request HttpServletRequest
     * @return ErrorEntity
     * @see ErrorEntity
     */
    @ResponseBody
    @RequestLogging
    @ExceptionHandler(IllegalValidateException.class)
    public Object illegalValidateException(Exception e, HttpServletRequest request) {
        // 取出存储在Request域中的Map
        return request.getAttribute(e.getMessage());
    }

    @ResponseBody
    @RequestLogging
    @ExceptionHandler(HystrixRuntimeException.class)
    public Object hystrixRuntimeException(HystrixRuntimeException e, HttpServletRequest request) {
        final Throwable fallbackException = e.getFallbackException().getCause().getCause();
        if (RestStatusException.class.isAssignableFrom(fallbackException.getClass())) {
            final String statusCode = fallbackException.getMessage();
            final StatusCode error = StatusCode.valueOfCode(Integer.valueOf(statusCode));
            return new ErrorEntity(error);
        }
        return exception(e, request);
    }

    @ResponseBody
    @RequestLogging
    @ExceptionHandler(Exception.class)
    public ErrorEntity exception(Exception e, HttpServletRequest request) {
        LOGGER.error("request id: {}\r\nexception: {}", request.getAttribute(RequestAttributeConst.REQUEST_ID), e);
        final RestStatus status = EXCEPTION_MAPPINGS.get(e.getClass());
        final ErrorEntity error;
        if (status != null) {
            error = new ErrorEntity(status);
        } else {
            error = new ErrorEntity(StatusCode.SERVER_UNKNOWN_ERROR);
        }
        return error;
    }

}
