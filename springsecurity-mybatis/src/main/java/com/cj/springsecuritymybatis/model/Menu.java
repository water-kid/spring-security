package com.cj.springsecuritymybatis.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Menu {
    private Integer id;
    private String pattern;
    // 访问该资源所需要的角色
    private List<Role> roleList;
}
