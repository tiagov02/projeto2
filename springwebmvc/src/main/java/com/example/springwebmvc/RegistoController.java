package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.CodPostaisCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
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
import javax.servlet.http.HttpSession;

@Controller
public class RegistoController {

    @GetMapping(value = "/registar")
    public String getRegistar(Model model){
        ModelCliente cli=new ModelCliente();
        model.addAttribute("cli",cli);
        return "registar";
    }

    @PostMapping(value="/registado")
    public String regista(@ModelAttribute ModelCliente cli,Model model, HttpSession session){
        String pwd="";
        Cliente cliente=new Cliente();
        try{
            pwd= Encriptacao.encript(cli.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Codpostais cod= CodPostaisCRUD.findCodPostal(cli.getCodpostal());
        if(cod==null){
            Codpostais cd= new Codpostais();
            cd.setLocalidade(cli.getLocalidade());
            cd.setCodpostal(cli.getCodpostal());
            CodPostaisCRUD.create(cd);
        }
        cliente.setPassword(pwd);
        cliente.setUsername(cli.getUsername());
        cliente.setIdtipocliente(1);
        cliente.setCodpostal(cli.getCodpostal());
        cliente.setNome(cli.getNome());
        cliente.setTelefone(cli.getTelefone());
        cliente.setNumporta(cli.getNumporta());
        cliente.setRua(cli.getRua());
        try{
            ClienteCRUD.createCliente(cliente);
        }catch (PersistenceException ex){
            model.addAttribute("mensagem","Houve um erro na criação! pf tente mais tarde ou contacte o administrados!!");
            return "error";
        }
        session.setAttribute("UserLogged",cliente);
        return "redirect:/produto";
    }

}
