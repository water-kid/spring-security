package com.cj.springsecurity;

import com.cj.springsecurity.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "hello";
    }
    @GetMapping("/dba/hello")
    public String dba(){
        return "dba";
    }

    @GetMapping("/admin/hello")
    public String admin(){
        return "admin";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "user";
    }

    @Autowired
    MethodService methodService;

    @GetMapping("/hello1")
    public String hello1(){
        return methodService.admin();
    }

    @GetMapping("/hello2")
    public String hello2(){
        return methodService.user();
    }
}
