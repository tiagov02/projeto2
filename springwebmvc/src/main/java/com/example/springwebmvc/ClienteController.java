package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ClienteController {

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }




}
