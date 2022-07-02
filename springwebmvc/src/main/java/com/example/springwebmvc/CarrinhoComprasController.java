package com.example.springwebmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarrinhoComprasController {
    @GetMapping(value="/addCarrinhoCompras/{idprod}")
    public String addCarrinhoCompras(){
        return "carrinhoCompras";
    }
}
