package com.hhly.mybatis.springbootmybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.mybatis.springbootmybatis.mapper.CountryMapper;
import com.hhly.mybatis.springbootmybatis.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {
	@Autowired
	private CountryMapper countryMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test(){
		Example example = new Example(Country.class);
		example.createCriteria().andGreaterThan("id",100);
		PageHelper.startPage(2,10);
		List<Country> countries = countryMapper.selectByExample(example);
		PageInfo<Country> pageInfo = new PageInfo<Country>(countries);
		System.out.println(pageInfo.getTotal());

		countries = countryMapper.selectByExample(example);
		pageInfo = new PageInfo<Country>(countries);
		System.out.println(pageInfo.getTotal());
	}
}
