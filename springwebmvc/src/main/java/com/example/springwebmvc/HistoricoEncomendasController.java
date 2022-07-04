package com.example.springwebmvc;

import com.example.bd.CRUD.EstadoFaturaCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Estado;
import com.example.bd.Entity.Estadofatura;
import com.example.bd.Entity.Fatura;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
    public String getDetalhesEncomenda(HttpSession session,@RequestParam int numfatura, Model model){
        Fatura fat=FaturaCRUD.findFatura(numfatura);
        Estado ef= EstadoFaturaCRUD.getUltimoEstadoFatura(numfatura);
        if(fat.getIdcliente() != ((Cliente) session.getAttribute("UserLogged")).getIdcliente() ){
            model.addAttribute("mensagem","Unauthorized");
            return "error";
        }
        model.addAttribute("fatura",fat);
        model.addAttribute("estado",ef.getDescricao());
        return "detalhesencomenda";
    }
}