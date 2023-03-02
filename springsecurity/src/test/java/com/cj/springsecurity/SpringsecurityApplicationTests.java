package com.cj.springsecurity;

import com.cj.springsecurity.model.Menu;
import com.cj.springsecurity.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringsecurityApplicationTests {

    @Test
    void contextLoads() {

        for (int i = 0; i < 10; i++) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            System.out.println(encoder.encode("123"));
        }
    }

    @Autowired
    MenuService menuService;
    @Test
    public void test01(){
        List<Menu> allMenus = menuService.getAllMenus();
        System.out.println("allMenus = " + allMenus);
    }

}
