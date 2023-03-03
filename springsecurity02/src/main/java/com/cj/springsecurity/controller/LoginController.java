package com.cj.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    @ResponseBody
    public String index(){
        return "index";
    }

    @GetMapping("/err")
    public String error(Model model){
        model.addAttribute("msg","error....");
        return "login";
    }
}
