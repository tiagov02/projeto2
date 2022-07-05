package com.example.springwebmvc;

import com.example.bd.CRUD.LinhaFaturaCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Linhafatura;
import com.example.bd.Entity.Produto;
import com.example.springwebmvc.ModelClasses.ModelFatura;
import com.example.springwebmvc.ModelClasses.ModelLinhaFatura;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * ESTA A ADICIONAR SÓ 1 PRODUTO --> MUDAR
 *
 */
@Controller
public class CarrinhoComprasController {

    @GetMapping(value="/addCarrinhoCompras")
    public String addCarrinhoCompras(HttpSession session, @RequestParam int idprod, @RequestParam int qtd, Model model){
        //int qtd=1;
        float valTotal=0;
        if(session.getAttribute("UserLogged") == null){
            return "redirect:/login";
        }
        Produto prod= ProdutoCRUD.findProduto(idprod);
        if(session.getAttribute("carrinho")==null){
            session.setAttribute("carrinho",new ModelFatura());
        }
        ModelLinhaFatura linha=new ModelLinhaFatura();
        linha.setIdProd(prod.getNumproduto());
        linha.setNomeProd(prod.getNome());
        linha.setQuant(qtd);
        linha.setPreco(prod.getValorunitariototal().floatValue()*qtd);
        linha.setPrecoUnitario(prod.getValorunitariototal().floatValue());

        ((ModelFatura) session.getAttribute("carrinho")).getLinhaFat().add(linha);

        for(ModelLinhaFatura l:((ModelFatura) session.getAttribute("carrinho")).getLinhaFat()){
            valTotal+=l.getPreco();
        }
        ((ModelFatura) session.getAttribute("carrinho")).setValTotal(valTotal);
        model.addAttribute("carrinho",((ModelFatura) session.getAttribute("carrinho")));
        return "carrinhoCompras";
    }

    @GetMapping("/carrinhoCompras")
    public String getCarrinhoCompras(HttpSession session,Model model){
        if(session.getAttribute("carrinho") == null){
            return "redirect:/produto";
        }
        model.addAttribute("carrinho",((ModelFatura) session.getAttribute("carrinho")));
        return "carrinhoCompras";
    }

    @PostMapping("/updateqtd")
    public String updateQtd(@ModelAttribute ModelLinhaFatura linha,HttpSession session, Model model){
        //
        if(session.getAttribute("UserLogged") == null) {
            return "redirect:/login";
        }
        if(session.getAttribute("carrinho") == null){
            return "redirect:/produto";
        }
        return "redirect:/carrinhoCompras";
    }

}
