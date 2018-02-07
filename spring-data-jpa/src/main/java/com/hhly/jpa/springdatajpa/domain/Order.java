package com.hhly.jpa.springdatajpa.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hhly.jpa.springdatajpa.model.BasicDomain;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode //callSuper属性只有在子类中才能使用
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table(name = "t_order")
public class Order{
    private static final long serialVersionUID = 7490727266143841610L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;//主键

    private String orderCode;//订单编号

    private String productCode;//产品编码

    private Integer price;//正常情况，价格应该使用  浮点型数据类型

    private Integer num;//产品数量

    private Integer status;//订单状态

    private Long userId;//用户id

}