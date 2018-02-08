package com.hhly.jpa.springdatajpa;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhly.jpa.springdatajpa.controller.OrderController;
import com.hhly.jpa.springdatajpa.controller.UserController;
import com.hhly.jpa.springdatajpa.domain.Order;
import com.hhly.jpa.springdatajpa.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringDataJpaApplication.class)
public class MockTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        //mvc = MockMvcBuilders.standaloneSetup(userController).build();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }


    /**
     * attention:
     * Details：测试查询接口             get请求
     * @author wanli
     * 创建时间：2016-12-7 下午3:42:02
     * @throws Exception
     * void
     */
    @Test
    public void queryOrderTest() throws Exception {
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/orders/1")./*param("pageOffset", "0")
                        .param("pageSize", "10").param("orderColumn", "id").*/accept(MediaType.APPLICATION_JSON))
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }



    /**
     * attention:
     * Details：测试新增接口             post请求   此方式需要controller中方法参数前加@RequestBody
     * @author wanli
     * 创建时间：2016-12-7 下午3:42:02
     * @throws Exception
     * void
     */
    @Test
    public void addOrderTest() throws Exception {
        Order entity = new Order();
        entity.setNum(2);
        entity.setOrderCode("ac123544");
        entity.setProductCode("00014444");
        entity.setPrice(1000);
        entity.setStatus(0);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/v1/orders")
                //发送的数据是json数据
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //接受的数据也要是json格式，默认是xml格式
                .accept(MediaType.APPLICATION_JSON)
                //将实体转化为json格式的参数
                .content(mapper.writeValueAsString(entity)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }



    /**
     * attention:
     * Details：测试分页查询
     * @author chhliu
     * 创建时间：2016-12-7 下午3:42:02
     * @throws Exception
     * void
     */
    @Test
    public void getUserPage() throws Exception {
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users/page").param("pageNum", "1")
                        .param("pageSize", "10").param("username", "hhly4")
                        .accept(MediaType.APPLICATION_JSON)
                        )
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }




    /**
     * attention:
     * Details：测试插入，方式一，此方式需要controller中方法参数前没有@RequestBody
     * @author chhliu
     * 创建时间：2016-12-7 下午3:42:19
     * @throws Exception
     * void
     */
    @Test
    public void postUser() throws Exception{
        //使用param传递参数
        RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/users")
                .param("codeSnippet", "package")
                .param("codeUrl", "http://localhost:8080/code")
                .param("projectUrl", "http://localhost:8080")
                .param("userName", "chhliu")
                .param("sensitiveMessage", "love")
                .param("spriderSource", "CSDN")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mvc.perform(request)
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }

    /**
     * attention:
     * Details：测试插入，方式二，此方式需要controller中方法参数前加@RequestBody
     * @author chhliu
     * 创建时间：2016-12-7 下午3:42:19
     * @throws Exception
     * void
     */
    @Test
    public void postUser1() throws Exception{
        User entity = new User();
        entity.setUserName("xyhg");
        entity.setBalance(20000L);
        entity.setMobile("12546788447");
        entity.setSex(1);
        entity.setStatus(1);
        entity.setLoginPwd("123456");
        entity.setPwdSalt("dd455");

        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //实体转为json
                .content(mapper.writeValueAsString(entity)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }

    /**
     * attention:
     * Details：测试更新和插入类似
     * @author chhliu
     * 创建时间：2016-12-7 下午3:42:32
     * @throws Exception
     * void
     */
    @Test
    public void putGithubEntity() throws Exception{
        User entity = new User();
        entity.setId(45L);
        entity.setUserName("xyhg");
        entity.setBalance(20000L);
        entity.setMobile("12546788447");
        entity.setSex(1);
        entity.setStatus(1);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //实体转为json
                .content(mapper.writeValueAsString(entity)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }

    @Test
    public void deleteUser() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.delete("/api/v1/users/45");
        MvcResult result = mvc.perform(request)
                .andReturn();
        int statusCode = result.getResponse().getStatus();
        Assert.assertEquals(statusCode, 200);
        String body = result.getResponse().getContentAsString();
        System.out.println("body:"+body);
    }
}
