package com.qdc.demoeurekaprovider2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortController {
    @Value("${server.port}")//使用value注解注入port属性
    String port;
    @RequestMapping("/port")
    public String getPort(){
        return "hello,world,I'm from port:"+port;
    }
}
