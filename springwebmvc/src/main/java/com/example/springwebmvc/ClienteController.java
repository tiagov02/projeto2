package com.example.springwebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
