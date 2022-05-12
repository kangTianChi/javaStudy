package com.qdc.demoeurekaconsumerfeign.controller;

import com.qdc.demoeurekaconsumerfeign.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class UserController {
    @Autowired
    private UserClient userClient;
    @RequestMapping(value = "/hi")//在浏览器访问地址/test/hi
    public String hello(){
        return userClient.hello();
    }
    //重写hello方法处理/sayHi请求
    @RequestMapping(value = "/sayHi")
    public String hello(@RequestParam(value = "sleep_seconds")int sleep_seconds){
        return userClient.hello(sleep_seconds);
    }
}
