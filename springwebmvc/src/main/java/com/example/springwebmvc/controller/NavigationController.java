package com.example.springwebmvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class NavigationController {

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
