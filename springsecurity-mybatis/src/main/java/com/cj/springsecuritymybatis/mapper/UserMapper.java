package com.cj.springsecuritymybatis.mapper;

import com.cj.springsecuritymybatis.model.Role;
import com.cj.springsecuritymybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);

    List<Role> getRolesById(String id);
}
