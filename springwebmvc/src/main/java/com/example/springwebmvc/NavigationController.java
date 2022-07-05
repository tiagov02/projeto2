package com.example.springwebmvc;

import com.example.bd.CRUD.ProdutoCRUD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class NavigationController {


    @GetMapping("/teste")
    public String teste(HttpSession session){
        //((Cliente) session.getAttribute("UserLogged")).getIdcliente()
        //session.getAttribute("UserLogged");
        //session.getAttribute("teste"); result=null
        //System.out.println("ESTOU AQUI");
        return "error";
    }

}
