package com.hhly.jpa.springdatajpa.repositories;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Optional;

/**
 * 定制自己的基础Repository
 *
 * JpaRepository包含CrudRepository和PagingAndSortingRepository的内容
 * 并且支持样例匹配查询findAll(Example<S> var1)
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface MyBaseRepository <T, ID extends Serializable> extends JpaRepository<T, ID>{
    Optional<T> findById(ID primaryKey);
}
