package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.Entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ClienteController {
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


}
