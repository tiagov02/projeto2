package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Encrypt.*;
import com.example.springwebmvc.ModelClasses.LoginData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin(Model model){
        LoginData login = new LoginData();
        model.addAttribute("login",login);
        return "login";
    }

    @PostMapping(value = "/plogin")
    public String verificaLogin(@ModelAttribute LoginData login, Model model, HttpSession session) {
            try {
                //Integer qtd=null;
                Cliente cli = ClienteCRUD.login(login.getNomeUser(), Encriptacao.encript(login.getPassword()));
                session.setAttribute("UserLogged",cli);
                model.addAttribute("produtos", ProdutoCRUD.findTodosProdutos());
                model.addAttribute("quantidade",1);
                return "clientelogado";
            } catch (Exception ex){
                return "error";
            }
    }
}
