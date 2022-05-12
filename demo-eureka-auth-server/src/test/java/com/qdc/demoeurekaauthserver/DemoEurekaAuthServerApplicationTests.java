package com.qdc.demoeurekaauthserver;

import com.alibaba.druid.pool.DruidDataSource;

import com.qdc.demoeurekaauthserver.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;


@SpringBootTest
class DemoEurekaAuthServerApplicationTests {
    @Autowired
    private DruidDataSource druidDataSource;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    void contextLoads() {
        System.out.println(druidDataSource);
    }
    @Test
    public void testUserMapper(){
        System.out.println(userMapper.findByUsername("admin"));
    }
    //    @Test
//    public void testUserPassword(){
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
//    }
    @Test
    public void testUserDetails(){
        System.out.println(userDetailsService.loadUserByUsername("admin"));
    }
}
