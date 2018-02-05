package com.hhly.jpa.springdatajpa.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Zhao Junjian
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class OrderRequest extends RestfulRequest {

    private static final long serialVersionUID = -7089168357959601473L;

    /**
     * 订单查询中产品编号可以为空
     */
    @Min(1)
    @ApiModelProperty(value = "产品编号", required = true, example = "1")
    private Long productCode;

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "用户ID", required = true, example = "1")
    private Long userId;

}
