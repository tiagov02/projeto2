package com.example.springwebmvc;

import com.example.bd.CRUD.ProdutoCRUD;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProdutoController {
    @GetMapping("/")
    public String getIndex(Model model){
        int qtd=1;
        model.addAttribute("produtos", ProdutoCRUD.findTodosProdutos());
        model.addAttribute("quantidade",qtd);
        return "index";
    }


    @GetMapping(value = "/produto")
    public String getProdutos(Model model, HttpSession session) {
        int qtd=1;
        if(session.getAttribute("UserLogged") == null){
            return "redirect:/login";
        }
        model.addAttribute("produtos", ProdutoCRUD.findTodosProdutos());
        model.addAttribute("quantidade",qtd);
        return "clientelogado";
    }
}
