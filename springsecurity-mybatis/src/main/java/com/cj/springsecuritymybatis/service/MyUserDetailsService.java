package com.cj.springsecuritymybatis.service;

import com.cj.springsecuritymybatis.mapper.UserMapper;
import com.cj.springsecuritymybatis.model.Role;
import com.cj.springsecuritymybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userMapper.loadUserByUsername(username);
        if (user == null){
            throw new BadCredentialsException("用户名没找到");
        }

        List<Role> roleList = userMapper.getRolesById(user.getId());
        user.setRoleList(roleList);
        return user;
    }
}
