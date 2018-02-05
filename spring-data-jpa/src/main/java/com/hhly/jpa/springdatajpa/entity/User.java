package com.hhly.jpa.springdatajpa.entity;


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
@EqualsAndHashCode(callSuper = true)  //callSuper属性只有在子类中才能使用
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)//使用驼峰命名法
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
@Entity
@Table(name = "t_user")
public class User extends BasicDomain {
    private static final long serialVersionUID = -1288891508177236744L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    //使用驼峰命名法，能直接和数据库字段匹配
    private String userName;

    @Column(nullable = false)
    private String mobile;

    private String loginPwd;

    private String pwdSalt;

    private Long balance;
    @Column(nullable = false)
    private Integer sex;
    @Column(nullable = false)
    private Integer status;


}
