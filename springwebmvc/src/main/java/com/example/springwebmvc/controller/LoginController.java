package com.example.springwebmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private LoginController login;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(name="name", required=false, defaultValue="World") String name,Model model) {
        model.addAttribute("name",name);
        return "login";
    }
}
