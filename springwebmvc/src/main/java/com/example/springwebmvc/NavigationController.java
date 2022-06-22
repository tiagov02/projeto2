package com.example.springwebmvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NavigationController {
    @GetMapping("")
    public String showHomePage(){
        return "index";
    }

}
