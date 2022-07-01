package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.CodPostaisCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Codpostais;
import com.example.springwebmvc.ModelClasses.ModelCliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.PersistenceException;

@Controller
public class RegistoController {

    @GetMapping(value = "/registar")
    public String registar(Model model){
        ModelCliente cli=new ModelCliente();
        model.addAttribute("cli",cli);
        return "registar";
    }

    @PostMapping(value="/registado")
    public String regista(@ModelAttribute ModelCliente cli,Model model){
        String pwd="";
        try{
            pwd= Encriptacao.encript(cli.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        cli.setPassword(pwd);
        cli.setIdtipocliente(1);
        Codpostais cod= CodPostaisCRUD.findCodPostal(cli.getCodpostal());
        if(cod==null){
        }
        try{
        }catch(PersistenceException ex){
            return "error";
        }
        return "clientelogado";
    }
}
