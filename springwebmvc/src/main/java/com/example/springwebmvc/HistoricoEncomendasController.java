package com.example.springwebmvc;

import com.example.bd.CRUD.EstadoFaturaCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.Entity.*;
import com.example.springwebmvc.ModelClasses.ModelLinhaFatura;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoricoEncomendasController {
    @GetMapping("/minhasEncomendas")
    public String getEncomendas(HttpSession session, Model model){
        List<Fatura> faturascli= FaturaCRUD.getFaturasByIdCliente(((Cliente) session.getAttribute("UserLogged")).getIdcliente());
        model.addAttribute("faturas",faturascli);
        return "historicoencomendas";
    }

    @GetMapping("/detalheencomenda")
    public String getDetalhesEncomenda(@RequestParam int numfatura, Model model,HttpSession session){
        Fatura fat=FaturaCRUD.findFatura(numfatura);
        String ent= fat.getMoradaentregaByIdentrega().getRua() +" , "+fat.getMoradaentregaByIdentrega().getNumporta()+" , "+
                fat.getMoradaentregaByIdentrega().getCodpostal()+ " , "+
                fat.getMoradaentregaByIdentrega().getCodpostaisByCodpostal().getLocalidade();
        List<ModelLinhaFatura> linhas=new ArrayList<>();
        try{
            Estado ef= EstadoFaturaCRUD.getUltimoEstadoFatura(numfatura);
            model.addAttribute("estado",ef.getDescricao());
        }catch(NoResultException ex){
            model.addAttribute("estado","ERRO! Não existe nenhum estado para esta fatura!");
        }
        try {
            if(fat.getIdcliente() != ((Cliente) session.getAttribute("UserLogged")).getIdcliente() ){
                model.addAttribute("mensagem","Unauthorized");
                return "error";
            }
        }catch(NullPointerException ex){
            return "redirect:/login";
        }
        for(Linhafatura lf: fat.getLinhafaturasByNumfatura()){
            ModelLinhaFatura linha=new ModelLinhaFatura();
            linha.setPreco(lf.getPreco().floatValue());
            linha.setQuant(lf.getQuantidade());
            linha.setIdProd(lf.getNumproduto());
            linha.setNomeProd(lf.getProdutoByNumproduto().getNome());
            linhas.add(linha);
        }
        model.addAttribute("fatura",fat);
        model.addAttribute("linhasfatura",linhas);
        if(fat.getMoradaentregaByIdentrega().getRua().equals("LOJA")){
            model.addAttribute("entrega","Entrega em loja");
        }
        else{
            model.addAttribute("entrega",ent);
        }
        return "detalhesencomenda";
    }
    //
}