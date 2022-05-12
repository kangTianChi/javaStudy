package com.qdc.demoeurekaauthserver.mapper;


import com.qdc.demoeurekaauthserver.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from springcloud_user where username=#{username}")
    public User findByUsername(String username);//根据用户名查找用户
}
