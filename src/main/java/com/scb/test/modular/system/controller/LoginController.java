package com.scb.test.modular.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description 登录跳转控制
 * @Author zhangheng
 */

@Controller
public class LoginController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping({"/","/login"})
    public String login(){
        return "index";
    }

}
