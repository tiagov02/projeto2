package com.example.springwebmvc;

import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Produto;
import com.example.springwebmvc.ModelClasses.ModelFatura;
import com.example.springwebmvc.ModelClasses.ModelLinhaFatura;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

/**
 * ESTA A ADICIONAR SÓ 1 PRODUTO --> MUDAR
 *
 * a qtd vai ter se ser recebida por link??
 */
@Controller
public class CarrinhoComprasController {
    @GetMapping(value="/addCarrinhoCompras/{idprod}")
    public String addCarrinhoCompras(HttpSession session,@PathVariable int idprod){
        int qtd=1;
        float valTotal=0;
        Produto prod= ProdutoCRUD.findProduto(idprod);
        if(session.getAttribute("carrinho")==null){
            session.setAttribute("carrinho",new ModelFatura());
        }
        ModelLinhaFatura linha=new ModelLinhaFatura();
        linha.setIdProd(prod.getNumproduto());
        linha.setNomeProd(prod.getNome());
        linha.setQuant(qtd);
        linha.setPreco(prod.getValorunitariototal().floatValue()*qtd);

        ((ModelFatura) session.getAttribute("carrinho")).getLinhaFat().add(linha);

        for(ModelLinhaFatura l:((ModelFatura) session.getAttribute("carrinho")).getLinhaFat()){
            valTotal+=l.getPreco();
        }
        ((ModelFatura) session.getAttribute("carrinho")).setValTotal(valTotal);
        //System.out.println("ESTOU AQUI");
        return "carrinhoCompras";
    }
}
