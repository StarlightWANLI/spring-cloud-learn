package com.hhly.jpa.springdatajpa;

import com.google.common.collect.Lists;
import com.hhly.jpa.springdatajpa.domain.User;
import com.hhly.jpa.springdatajpa.repositories.UserRepository;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

//   @RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringDataJpaApplication.class)
public class SpringDataJpaApplicationTests {

    @Autowired
	UserRepository userRepository;

	@Autowired
	StringEncryptor stringEncryptor;

	@Test
	public void encryptPwd() {
		String result = stringEncryptor.encrypt("123456");
		System.out.println("jasypt密码加密：" +  result);
	}


	@Test
	public void contextLoads() {
	}


	@Test
	public void testJpa() {
		List<User> userList = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			 User user = new User();
			 user.setUserName("hhly"+i);
			 user.setLoginPwd("123456");
			 user.setMobile("123456789");
			user.setBalance(10000L);
			user.setPwdSalt("666666");
			user.setSex(1);
			user.setStatus(2);
			userList.add(user);
		}
		userRepository.save(userList);


		userList = userRepository.findAll();
		userList.forEach(e -> System.out.println( e ) );

		Optional<User> optional  = userRepository.findById(1L);
		optional.ifPresent((value) -> {
			System.out.println("The UserName of the value is: " + value.getUserName());
		});

		//page=1&size=20
		//分页查询
		//PageRequest的对象构造函数有多个，page是页数，初始值是0，size是查询结果的条数，后两个参数参考Sort对象的构造方法
		Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"id");
		Page<User> page = userRepository.findAll(pageable);
		//查询结果总行数
		System.out.println(page.getTotalElements());
		System.out.println(page.getTotalPages());



		userRepository.findAll();
	}



	@Test
	public void testFindAll() {
		List<User> userList = Lists.newArrayList();
		userList = userRepository.findAll();
		userList.forEach(e -> System.out.println( e ) );
	}


	@Test
	public void testFindOne() {
		User user =  userRepository.findOne(1L);
		System.out.println( user);
	}

	//分页查询
	@Test
   public void  testPageQuery(){
		int page=1,size=10;
		Sort sort = new Sort(Sort.Direction.DESC, "id");//id倒序排列
		Pageable pageable = new PageRequest(page, size, sort);
		Page<User> userList = userRepository.findAll(pageable);
		userList.forEach(e -> System.out.println( e ) );
   }

	@Test
	public void  testFindByNamePageQuery(){
		int page=1,size=10;
		Sort sort = new Sort(Sort.Direction.DESC, "id");//id倒序排列
		Pageable pageable = new PageRequest(page, size, sort);
		Page<User> userList = userRepository.findByUserName("hhly2",pageable);
		userList.forEach(e -> System.out.println( e ) );
	}

   //限制查询
	//返回满足条件的第一个值
   @Test
	public void testLimitTopQuery(){
		User user = userRepository.findTopByOrderByIdDesc();
		System.out.println( user);
	}

	//限制查询
	//返回满足条件的前10条记录
	@Test
	public void testLimitFirstQuery(){
		List<User> userList = userRepository.findFirst10ByUserNameLike("hhly%");
		userList.forEach(e -> System.out.println( e ) );
	}



	//自定义sql实现更新、修改操作
	@Test
	public void testSelfSql(){
		//userRepository.modifyUserNameById("修改",2L);
		userRepository.delete(2L);
	}


	//分别使用hql和sql查询返回实体对象
	@Test
	public void testSelfQuery(){
		List<User> userList = userRepository.findByMobile("125678");
		userList.forEach(e -> System.out.println( e ) );
		 userList = userRepository.findByStatus(9);
		userList.forEach(e -> System.out.println( e ) );
	}


	//命名查询
	@Test
	public void testNameQuery(){

	}




	//多表关联查询，返回单独对象（分为级联查询和自定义返回对象查询）



	//多表关联查询，返回多的字段



	public static void main(String[] args) {
		//PBEWithMD5AndDES
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword("root");
		String encrypted = encryptor.encrypt("123456");
		System.out.println(encrypted);
	}

}
