package com.qdc.demoeurekaprovider1.controller;

import com.qdc.demoeurekaprovider1.pojo.User;
import com.qdc.demoeurekaprovider1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Value("${spring.cloud.client.ip-address}")
    String idaddr;
    @Value("${server.port}")
    int port;
    @Autowired
    private UserService userService;
    //addUser方法在接收前端传来的数据也是JSON格式，需要将JSON格式转换成User对象
    @RequestMapping(value = "/add",method = RequestMethod.POST)//添加
    public boolean addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @RequestMapping(value = "/update",method = RequestMethod.PUT)//修改
    public boolean updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)//删除
    public boolean deleteUser(String name){
        return userService.deleteUser(name);
    }
    @RequestMapping(value = "/details",method = RequestMethod.GET)
    public User selectUserById(@RequestParam(value = "userid",required = true)String id){
        return userService.selectUserById(id);
    }
    @RequestMapping(value = "/userall",method = RequestMethod.GET)
    public List<User> selectUserById(){
        return userService.selectAllUser();
    }
    //熔断器
    //处理sayHi请求，参数sleep_seconds用于指定程序休眠的秒数。
    @RequestMapping(value = "/sayHi",method = RequestMethod.GET)
    public String hello(@RequestParam(value = "sleep_seconds",required = true)int sleep_seconds)throws  InterruptedException{
        System.out.println("休眠时间"+sleep_seconds);
        Thread.sleep(sleep_seconds*1000);
        return "Hello,我在"+idaddr+":"+port;

    }
}
