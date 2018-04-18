package com.springboot.oauth.authorize.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: 万里
 * @Date: 2018/4/17 15:47
 */
@RestController
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return  "Hello World";
    }
}
