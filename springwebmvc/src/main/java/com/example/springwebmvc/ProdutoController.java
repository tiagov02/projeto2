package com.example.springwebmvc;

import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("nomeuser",((Cliente) session.getAttribute("UserLogged")).getNome());
        return "clientelogado";
    }

    @GetMapping("/detalhesproduto")
    public String getDetalhesProduto(@RequestParam int idprod, Model model, HttpSession session){
        if(session.getAttribute("UserLogged") == null){
            return "redirect:/login";
        }
        Produto p=ProdutoCRUD.findProduto(idprod);
        if(p==null) return "redirect:/produto";
        model.addAttribute("produto",p);
        model.addAttribute("nomeuser",((Cliente) session.getAttribute("UserLogged")).getNome());
        return "detalhesProduto";
    }
}
