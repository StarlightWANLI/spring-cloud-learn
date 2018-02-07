package com.hhly.jpa.springdatajpa.repositories;

import com.hhly.jpa.springdatajpa.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends  MyBaseRepository<User, Long>{

      Page<User> findByUserName(String userName, Pageable pageable);
      User findTopByOrderByIdDesc();
      List<User> findFirst10ByUserNameLike(String userName);


      /**
       * 注意做修改、删除操作时，一定要加上@Modifying注解，
       * 如果是直接使用sql操作， nativeQuery =true
       * 如果使用的是hql针对对象的操作，不用添加nativeQuery =true，或配置nativeQuery =false
       * 另外使用spring data jpa使用要在启动类上加上 @EnableJpaRepositories("com.hhly.jpa.springdatajpa")
       * 开启事务功能，里面包含了//@EnableTransactionManagement
       * @param userName
       * @param id
       * @return
       */
      @Modifying
      @Transactional
      @Query(value = "update User u set u.userName = ?1 where u.id = ?2")
      int modifyUserNameById(String  userName, Long id);

      @Modifying
      @Transactional
      @Query("delete from User where id = ?1")
      void deleteByUserId(Long id);


      //使用自定义hql查询
      @Transactional(timeout = 10)
      @Query("select u from User u where u.mobile = ?1")
      List<User> findByMobile(String Mobile);


      //使用sql查询
      @Query(value = "select * from t_user u where  u.status = ?1",nativeQuery = true)
      List<User> findByStatus(Integer status);
}
