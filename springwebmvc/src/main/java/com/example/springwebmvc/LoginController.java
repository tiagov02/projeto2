package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Encrypt.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin(Model model){
        LoginData login = new LoginData();
        model.addAttribute("login",login);
        return "login";
    }

    @PostMapping(value = "/plogin")
    public String verificaLogin(@ModelAttribute LoginData login, Model model){
        System.out.println("USER" + login.getNomeUser() + "PASSWORD" + login.getPassword());
        try{
            try {
                Cliente cli = ClienteCRUD.login(login.getNomeUser(), Encriptacao.encript(login.getPassword()));
                return "clientelogado";
            } catch (Exception ex){
                return "error";
            }

        }catch (NoResultException ex){
            //model.addAttribute("erro", "Username ou password erradas");
            return "error";
        }
    }
}
