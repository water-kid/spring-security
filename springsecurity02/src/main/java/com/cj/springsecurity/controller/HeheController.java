package com.cj.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeheController {
    @GetMapping("/s")
    public String hehe(){
        return "hehe";
    }
}
