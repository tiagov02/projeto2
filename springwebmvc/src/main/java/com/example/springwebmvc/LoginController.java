package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Colaborador;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    /*@GetMapping("/login")
    public String getLogin(Model model){
        Cliente cli= new Cliente();
        model.addAttribute("cli",cli);
        return "login";
    }*/
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
    /*@RequestMapping(value = "/login",method = RequestMethod.POST)
    public String verificaLogin(@RequestParam String nomeutilizador, @RequestParam String pass, HttpSession session, Model model){
        Cliente cli = ClienteCRUD.login(nomeutilizador, pass);

        if (cli != null && cli.getPassword().equals(pass)){
            session.setAttribute("logincliente", cli);
            return "clienteLogado";
        }
        else {
            model.addAttribute("erro", "Username ou password erradas");
            return "login";
        }
    }*/
    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response){
        String userName=request.getParameter("user");
        String password=request.getParameter("pass");
        Cliente cli=ClienteCRUD.login(userName,password);
        if(cli==null){
            return new ModelAndView("error");
        }
        return new ModelAndView("registar");
    }
}
