package com.cj.springsecurity.service;

import com.cj.springsecurity.mapper.MenuMapper;
import com.cj.springsecurity.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getAllMenus(){
        return menuMapper.getAllMenus();
    }
}
