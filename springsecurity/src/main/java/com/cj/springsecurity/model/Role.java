package com.cj.springsecurity.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Role {
    private Integer id;
    private String name;
    private String nameZh;

}
