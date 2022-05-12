package com.qdc.demoeurekaauthserver.service.impl;


import com.qdc.demoeurekaauthserver.pojo.Role;
import com.qdc.demoeurekaauthserver.pojo.User;
import com.qdc.demoeurekaauthserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.getUser(username);
        System.out.println(user);
        if (user==null || user.getId()<1){
            throw new UsernameNotFoundException("not found"+username);
        }
        /**
         * 查询成功，用户存储，需要匹配用户名和密码是否正确
         * 匹配密码，是由SpringSecurity内部逻辑自动完成，只需要把查询用户名和密码返回
         * 返回结果，是由UserDetails接口实现类型User，构造的时候需要提供7个参数
         * 用户名、密码、已启用账户、账户是否过期、账户是否被锁定、登录用户权限集合
         */
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                true,true,true,true,getGrantAuthoritest(user));
    }

    private Collection<? extends GrantedAuthority> getGrantAuthoritest(User user) {
        Set<Role> roles=new HashSet<>();
        Role r=new Role(1,"admin");
        roles.add(r);
        user.setRoles(roles);
        Set<GrantedAuthority> authorities=new HashSet<>();//权限集合
        for (Role role:user.getRoles()){//权限认证
            authorities.add(new SimpleGrantedAuthority("ROLE"+role.getName()));
            System.out.println(role);
        }
        return authorities;
    }
}
