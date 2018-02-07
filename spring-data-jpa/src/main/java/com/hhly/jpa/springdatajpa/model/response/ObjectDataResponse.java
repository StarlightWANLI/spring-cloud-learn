package com.hhly.jpa.springdatajpa.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * 返回单个对象的封装对象
 * @author Zhao Junjian
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ObjectDataResponse<T> extends RestfulResponse {
    private static final long serialVersionUID = 1862906172390850647L;

    private T data;

    public ObjectDataResponse(T data) {
        this.data = data;
    }
}
