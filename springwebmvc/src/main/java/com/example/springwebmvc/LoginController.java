package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Encrypt.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin(Model model){
        loginData login = new loginData();
        model.addAttribute("login",login);
        return "login";
    }
/*
    @PostMapping("/plogin")
    public String plogin(@ModelAttribute Cliente cli, Model model){
        System.out.println("USER: "+cli.getUsername());
        String pass="";
        try{
            pass= Encriptacao.encript(cli.getPassword());
        }
        catch(Exception e){
            //throw new RunTimeError("Ocorreu um erro no programa, por favor tente de novo dentro de alguns minutos");
            return "error";
        }
        Cliente c= ClienteCRUD.login(cli.getUsername(), pass);
        System.out.println("USER: "+cli.getUsername()+"\n\nPASSWORD"+cli.getPassword());

        if(c==null){
            return "error";
        }
        return "registar";
    }



 */
    @PostMapping(value = "/plogin")
    public String verificaLogin(@ModelAttribute loginData login, Model model){
        System.out.println("USER" + login.getNomeUser() + "PASSWORD" + login.getPassword());

        Cliente cli = ClienteCRUD.login(login.getNomeUser(), login.getPassword());


        if (cli != null){
            return "clienteLogado";
        }
        else {
            model.addAttribute("erro", "Username ou password erradas");
            return "login";
        }

    }

    /**
     * PARA APAGAR LINK ABAIXO
     */
    //link https://www.w3schools.blog/login-spring-mvc
    //APARECE ERRO
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        String userName=request.getParameter("user");
        String password=request.getParameter("pass");
        try{
            Cliente cli=ClienteCRUD.login(userName,password);
            return new ModelAndView("registar");
        } catch(NoResultException ex){
            return new ModelAndView("error");
        }
    }
}
