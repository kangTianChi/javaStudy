package com.qdc.demoeurekaconsumerfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-provider1" ,fallback = FeignClientFallBack.class)//使用注解FeignClient
// name属性设置服务提供者名 对应处理的请求是eureka-provider1
public interface UserClient {
    @RequestMapping(value = "/port")
    public String hello();

    @RequestMapping(value = "/user/sayHi")
    public String hello(@RequestParam(value ="sleep_seconds") int sleep_seconds);

}
