package com.lxg.spring.entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * catalog: 对应关系数据库中的catalog,如果在entitybean中设置此属性，将在表名前面加上catalog指定的值，
 * 注意catalog的值一定是数据库的名称，不然会生成的sql语句回报找不到表的错误。
 */
@Entity
@Table(name = "s_role", catalog = "test")
public class SRole implements java.io.Serializable {



    private Integer id;

    private SUser SUser;

    private String name;



    public SRole() {

    }



    public SRole(SUser SUser) {

       this.SUser = SUser;

    }



    public SRole(SUser SUser, String name) {

       this.SUser = SUser;

       this.name = name;

    }



    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {

       return this.id;

    }



    public void setId(Integer id) {

       this.id = id;

    }



    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "uid", nullable = false)

    public SUser getSUser() {

       return this.SUser;

    }



    public void setSUser(SUser SUser) {

       this.SUser = SUser;

    }



    @Column(name = "name", length = 20)

    public String getName() {

       return this.name;

    }



    public void setName(String name) {

       this.name = name;

    }



}