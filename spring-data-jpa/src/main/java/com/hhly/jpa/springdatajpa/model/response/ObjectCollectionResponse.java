package com.hhly.jpa.springdatajpa.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 *
 * 返回集合的封装实体
 *
 * @author Zhao Junjian
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ObjectCollectionResponse<T> extends RestfulResponse {
    private static final long serialVersionUID = 1862906172390850647L;

    private Collection<T> dataSet;

    public ObjectCollectionResponse(Collection<T> dataSet) {
        this.dataSet = ImmutableList.copyOf(dataSet);
    }
}
