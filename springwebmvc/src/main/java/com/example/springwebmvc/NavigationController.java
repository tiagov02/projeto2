package com.example.springwebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class NavigationController {

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/teste")
    public String teste(HttpSession session){
        //((Cliente) session.getAttribute("UserLogged")).getIdcliente()
        session.getAttribute("UserLogged");
        return "error";
    }
}
