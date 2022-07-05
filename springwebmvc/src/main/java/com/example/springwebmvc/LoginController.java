package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Encrypt.*;
import com.example.springwebmvc.ModelClasses.LoginData;
import com.example.springwebmvc.ModelClasses.ModelFatura;
import com.example.springwebmvc.ModelClasses.ModelLinhaFatura;
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
        int qtd=1;
            try {
                Cliente cli = ClienteCRUD.login(login.getNomeUser(), Encriptacao.encript(login.getPassword()));
                session.setAttribute("UserLogged",cli);
                return "redirect:/produto";
            } catch (Exception ex){
                model.addAttribute("mensagem","User ou pass errados!");
                return "redirect:/login";
            }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        if(session.getAttribute("UserLogged") != null){
            session.setAttribute("UserLogged",null);
        }
        return "redirect:/";
    }
}
