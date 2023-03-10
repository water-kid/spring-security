package com.cj.springsecurity.mapper;

import com.cj.springsecurity.model.Role;
import com.cj.springsecurity.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User loadUserByUsername(String username);

    @Select("select r.* from role r,user_role ur  where r.id=ur.rid and ur.uid =#{id}")
    List<Role> getRolesById(String id);
}
