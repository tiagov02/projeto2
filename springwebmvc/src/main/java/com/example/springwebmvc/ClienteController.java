package com.example.springwebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/registar")
    public String getRegistar(){
        return "registar";
    }

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/carrinhoCompras")
    public String getCarrinhoCompras(){
        return "carrinhoCompras";
    }

    @GetMapping("/clienteLogado")
    public String getClienteLogado(){
        return "clienteLogado";
    }



}
