package com.hhly.jpa.springdatajpa.controller;


import com.github.pagehelper.PageInfo;
import com.hhly.jpa.springdatajpa.annatation.RequestLogging;
import com.hhly.jpa.springdatajpa.domain.User;
import com.hhly.jpa.springdatajpa.model.ResultMsg;
import com.hhly.jpa.springdatajpa.repositories.UserRepository;
import com.hhly.jpa.springdatajpa.service.UserService;
import com.hhly.jpa.springdatajpa.util.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用produces和consumes现在消费和返回的都是json格式的字符串
 */
@RestController
@RequestMapping(value = "/api/v1")
@Api(description="用户相关操作接口")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository  userRepository;


    /**
     * 控制层尽可能简单，只是提供请求的接受入口，业务逻辑尽量在服务层完成
     *
     * 使用get请求利用路径传递参数要注意， spring mvc并不能根据请求url中的数据类型来分区具体对应的哪个请求，比如：
     * 请求  /users/1  不能识别映射到那个路径
     * /users/{username}
     * /users/{id}
     *
     * @Valid是用来校验实体类中指定属性的校验规则
     * @return
     */
    @ApiOperation(value = "根据用户名查询用户信息", notes = "查询用户信息")
    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    @RequestLogging
    public ResultMsg<List<User>> getUserByUsername(@PathVariable("username") final String username){
        ResultMsg<List<User>> res = new ResultMsg<List<User>>();
        try {
            Assert.hasLength(username, "username must be not null!");
            //服务层返回的具体的数据，控制层针对情况进行封装
            User user = new User();
            user.setUserName(username);
            List<User> list = userService.query(user);
            res.setResponseObject(list);
            res.setOK(true);
        } catch (Exception e) {
            res.setMsg(e.getMessage());
            res.setOK(false);
        }
        return res;
    }



    @ApiOperation(value = "更加用户id查询用户信息", notes = "查询用户信息")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @RequestLogging
    public ResultMsg<User> getUserByUsername(@PathVariable("id") final Long id){
        ResultMsg<User> res = new ResultMsg<User>();
        try {
            User user = new User();
            user.setId(id);
            List<User> list = userService.query(user);
            if(list!=null && list.size()>0){
                res.setResponseObject(list.get(0));
                res.setOK(true);
            }else{
                res.setMsg("the record id is not exist!");
                res.setOK(false);
            }
        } catch (Exception e) {
            res.setMsg(e.getMessage());
            res.setOK(false);
        }
        return res;
    }


    /**
     * attention:
     * Details：查询所有的结果，并分页和排序
     * @author chhliu
     */
    @RequestMapping(value="/users/page", method=RequestMethod.GET)
    @RequestLogging
    public ResultMsg<Map<String,Object>> findPage(final Integer pageNum, final Integer pageSize, final String username){
        ResultMsg<Map<String,Object>> res = new ResultMsg<Map<String,Object>>();
        try {
            User user = new User();
            user.setUserName(username);
            user.setPageNum(pageNum);
            user.setPageSize(pageSize);
/*
  mybatis的pageInfo对象中，pageNum表示当前页码，pageSize每页多少条记录，size表示当前页实际存在多少记录
      "pageInfo": {"pageNum": 15,"pageSize": 3,"size": 2
*/

            Map<String,Object> map = new HashMap<>();
            map.put("pageInfo", new PageInfo<User>(userService.query(user)));
/*            map.put("page", country.getPage());
            map.put("rows", country.getRows());*/

            res.setResponseObject(map);
            res.setOK(true);
        } catch (Exception e) {
            res.setMsg(e.getMessage());
            res.setOK(false);
        }
        return res;
    }


    /**
     * attention:
     * Details：插入一个实体到数据库中
     * @author chhliu
     */
    @RequestMapping(value="/users" ,method=RequestMethod.POST)
    @RequestLogging
    public ResultMsg<User> saveUser(@RequestBody final User entity){
        ResultMsg<User> res = new ResultMsg<User>();
        try {
            Assert.notNull(entity, "the insert record must not be null!");
            res.setResponseObject(userService.save(entity));
            res.setOK(true);
        } catch (Exception e) {
            res.setMsg(e.getMessage());
            res.setOK(false);
        }
        return res;
    }


    /**
     * attention:
     * Details：
     * @author chhliu
     */
    @RequestMapping(value="/users" ,method=RequestMethod.PUT)
    @RequestLogging
    public ResultMsg<User> updateUser(@RequestBody final User entity){
        ResultMsg<User> res = new ResultMsg<User>();
        //不是很推荐在控制层调用dao层，这样的方法通用性太差，不能别其他类调用
        try {
            Assert.notNull(entity, "the update record must not be null!");
            Assert.notNull(entity.getId(), "the record id must not be null!");

            //如果是mybatis，感觉可以直接使用更新
            boolean isExists = userRepository.exists(entity.getId());
            if(isExists){
                User en = userRepository.findOne(entity.getId());
                if(null != en){
                    BeanUtils.copyPropertiesIgnoreNull(entity,en);
                    res.setResponseObject(userRepository.save(en));
                    res.setOK(true);
                }else{
                    res.setOK(false);
                    res.setMsg("doesn't find the record by id!");
                }
            }else{
                res.setOK(false);
                res.setMsg("the record id is not exist!");
            }

        } catch (Exception e) {
            res.setMsg(e.getMessage());
            res.setOK(false);
        }
        return res;
    }


    /**
     * attention:
     * Details：根据id删除一条数据
     * @author chhliu
     */
    @RequestMapping(value="users/{id}", method=RequestMethod.DELETE)
    @RequestLogging
    public ResultMsg<Boolean> deleteUser(@PathVariable("id") final Long id){
        ResultMsg<Boolean> res = new ResultMsg<Boolean>();
        try {
            Assert.notNull(id,"id 不能为空");
            boolean isExist = userRepository.exists(id);
            if(isExist){
                userRepository.delete(id);
                res.setOK(true);
                res.setResponseObject(true);
            }else{
                res.setOK(false);
                res.setMsg("the record id is not exist!");
            }
        } catch (Exception e) {
            res.setMsg(e.getMessage());
            res.setOK(false);
        }
        return res;
    }
}
