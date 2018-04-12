package com.lxg.spring.controller;

import com.lxg.spring.service.SUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private SUserService sUserService;
    @RequestMapping(value = {"/","index"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/regist")
    public String regist(){
        return "regist";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/mine")
    public String mine(){
        return "mime";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(){
        return "loginSuccess";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/403")
    public String error(){
        return "403";
    }
}
