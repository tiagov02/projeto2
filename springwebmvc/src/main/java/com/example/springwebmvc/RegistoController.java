package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.PersistenceException;

@Controller
public class RegistoController {

    @GetMapping(value = "/registo")
    public String registar(Model model){
        Cliente cli=new Cliente();
        model.addAttribute("cli",cli);
        return "registar";
    }

    @PostMapping(value="/registado")
    public String regista(@ModelAttribute Cliente cli,Model model){
        String pwd="";
        try{
            pwd= Encriptacao.encript(cli.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        cli.setPassword(pwd);
        try{
            ClienteCRUD.createCliente(cli);
        }catch(PersistenceException ex){
            return "error";
        }
        return "clientelogado";
    }
}
