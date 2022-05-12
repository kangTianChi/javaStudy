package com.qdc.demoeurekaauthserver.service.impl;


import com.qdc.demoeurekaauthserver.mapper.UserMapper;
import com.qdc.demoeurekaauthserver.pojo.User;
import com.qdc.demoeurekaauthserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String username) {
        return userMapper.findByUsername(username);
    }
}
