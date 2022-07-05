package com.example.springwebmvc;

import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.CRUD.LinhaFaturaCRUD;
import com.example.bd.CRUD.MoradaEntregaCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.*;
import com.example.springwebmvc.ModelClasses.ModelFatura;
import com.example.springwebmvc.ModelClasses.ModelLinhaFatura;
import com.example.springwebmvc.ModelClasses.ModelMorada;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * ESTA A ADICIONAR SÓ 1 PRODUTO --> MUDAR
 *
 */
@Controller
public class CarrinhoComprasController {

    @GetMapping(value="/addCarrinhoCompras")
    public String addCarrinhoCompras(HttpSession session, @RequestParam int idprod, @RequestParam int qtd, Model model){
        //int qtd=1;
        boolean containsProduct=false;
        int auxQtd=0;
        float valTotal=0;
        if(session.getAttribute("UserLogged") == null){
            return "redirect:/login";
        }
        Produto prod= ProdutoCRUD.findProduto(idprod);
        if(session.getAttribute("carrinho")==null){
            session.setAttribute("carrinho",new ModelFatura());
        }
        for(ModelLinhaFatura lf: ((ModelFatura) session.getAttribute("carrinho")).getLinhaFat()) {
            if(idprod == lf.getIdProd()){
                auxQtd=lf.getQuant() + qtd;
                lf.setQuant(auxQtd);
                lf.setPreco(auxQtd*lf.getPrecoUnitario());
                containsProduct=true;
            }
        }
        if(!containsProduct){
            ModelLinhaFatura linha=new ModelLinhaFatura();
            linha.setIdProd(prod.getNumproduto());
            linha.setNomeProd(prod.getNome());
            linha.setQuant(qtd);
            linha.setPreco(prod.getValorunitariototal().floatValue()*qtd);
            linha.setPrecoUnitario(prod.getValorunitariototal().floatValue());

            ((ModelFatura) session.getAttribute("carrinho")).getLinhaFat().add(linha);
        }
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
        if(session.getAttribute("UserLogged") == null) {
            return "redirect:/login";
        }
        if(session.getAttribute("carrinho") == null){
            return "redirect:/produto";
        }
        return "redirect:/carrinhoCompras";
    }

    @GetMapping(value="/passo2")
    public String getPasso2(@RequestParam String selectEntrega, HttpSession session, Model model){
        if(session.getAttribute("UserLogged") == null){
            return "redirect:/login";
        }
        if(session.getAttribute("carrinho") == null){
            return "redirect:/produto";
        }
        //Se o user escolher entregar na morada predefinida
        if(selectEntrega.equals("user")){
            return "redirect:/terminarEncUser";
        }
        //se for na loja
        if(selectEntrega.equals("loja")){
            return "redirect:/terminarEncLoja";
        }
        //Se o cliente quiser numa Morada diferente
        if(selectEntrega.equals("novamorada")){
            return "redirect:/selecionarMorada";
        }
        model.addAttribute("mensagem","Houve um erro da nossa parte, pf tente mais tarde" +
                "ou contacte o suporte da loja!");
        return "error";
    }

    /**
     * CONTROLLERS CONDICIONAIS DEPENDENDO DA FORMA DE ENTREGA
     */
    //Morada predefinida do user
    @GetMapping("/terminarEncUser")
    public String terminarEncMoradaUser(HttpSession session,Model model){
        if(session.getAttribute("UserLogged") == null){
            return "redirect:/login";
        }
        if(session.getAttribute("carrinho") == null){
            return "redirect:/produto";
        }
        Moradaentrega mor= new Moradaentrega();
        Fatura fat= new Fatura();
        mor.setCodpostal(((Cliente) session.getAttribute("UserLogged")).getCodpostal());
        mor.setNumporta(((Cliente) session.getAttribute("UserLogged")).getNumporta());
        mor.setRua(((Cliente) session.getAttribute("UserLogged")).getRua());
        try{
            MoradaEntregaCRUD.createMoradaEntrega(mor);
        }catch (PersistenceException ex){
            model.addAttribute("mensagem","Houve um erro na sua encomenda. Pf tente mais tarde");
            return "error";
        }
        Calendar calendar = Calendar.getInstance();
        fat.setIdcliente(((Cliente) session.getAttribute("UserLogged")).getIdcliente());
        fat.setData(new java.sql.Date((calendar.getTime()).getTime()));
        //CRIADO ESTE USER WEBAPI
        fat.setIdcolaborador(12);
        fat.setIdentrega(mor.getIdentrega());
        fat.setValorfatura(new BigDecimal(((ModelFatura) session.getAttribute("carrinho")).getValTotal()));
        try{
            FaturaCRUD.createFatura(fat);
        }catch (PersistenceException ex){
            model.addAttribute("mensagem","Houve um erro na sua encomenda. Pf tente mais tarde");
            return "error";
        }
        for(ModelLinhaFatura lf: ((ModelFatura) session.getAttribute("carrinho")).getLinhaFat()){
            Linhafatura linha=new Linhafatura();
            linha.setNumfatura(fat.getNumfatura());
            linha.setQuantidade(lf.getQuant());
            linha.setNumproduto(lf.getIdProd());
            linha.setPreco(new BigDecimal(lf.getPreco()));
            try{
                LinhaFaturaCRUD.createLinhaFatura(linha);
            }catch (PersistenceException ex){
                try {FaturaCRUD.deleteFatura(fat.getNumfatura());} catch (IdNaoEncontradoException e) {}
                model.addAttribute("mensagem","Houve um erro na sua encomenda. Pf tente mais tarde");
                return "error";
            }
        }
        return ("/detalheencomenda?numfatura="+fat.getNumfatura());
    }
    //se for na loja
    @GetMapping("/terminarEncLoja")
    public String terminarEncMoradaLoja(HttpSession session, Model model){
        if(session.getAttribute("UserLogged") == null){
            return "redirect:/login";
        }
        if(session.getAttribute("carrinho") == null){
            return "redirect:/produto";
        }
        Moradaentrega mor;
        Fatura fat= new Fatura();
        try{
            mor=MoradaEntregaCRUD.getMoradaLoja();
        }catch (NoResultException ex){
            mor=new Moradaentrega();
            mor.setRua("LOJA");
            mor.setCodpostal("4444-444");
            mor.setNumporta(2);
            try{
                MoradaEntregaCRUD.createMoradaEntrega(mor);
            }catch (PersistenceException exept){
                model.addAttribute("mensagem","Houve um erro na sua encomenda. Pf tente mais tarde");
                return "error";
            }
        }
        Calendar calendar = Calendar.getInstance();
        fat.setIdcliente(((Cliente) session.getAttribute("UserLogged")).getIdcliente());
        fat.setData(new java.sql.Date((calendar.getTime()).getTime()));
        //CRIADO ESTE USER WEBAPI
        fat.setIdcolaborador(12);
        fat.setIdentrega(mor.getIdentrega());
        fat.setValorfatura(new BigDecimal(((ModelFatura) session.getAttribute("carrinho")).getValTotal()));
        try{
            FaturaCRUD.createFatura(fat);
        }catch (PersistenceException ex){
            model.addAttribute("mensagem","Houve um erro na sua encomenda. Pf tente mais tarde");
            return "error";
        }
        for(ModelLinhaFatura lf: ((ModelFatura) session.getAttribute("carrinho")).getLinhaFat()){
            Linhafatura linha=new Linhafatura();
            linha.setNumfatura(fat.getNumfatura());
            linha.setQuantidade(lf.getQuant());
            linha.setNumproduto(lf.getIdProd());
            linha.setPreco(new BigDecimal(lf.getPreco()));
            try{
                LinhaFaturaCRUD.createLinhaFatura(linha);
            }catch (PersistenceException ex){
                try {FaturaCRUD.deleteFatura(fat.getNumfatura());} catch (IdNaoEncontradoException e) {}
                model.addAttribute("mensagem","Houve um erro na sua encomenda. Pf tente mais tarde");
                return "error";
            }
        }
        return ("/detalheencomenda?numfatura="+fat.getNumfatura());
    }
    //Se o cliente quiser numa Morada diferente
    @GetMapping(value = "/selecionarMorada")
    public String getMorada(Model model, HttpSession session) {
        ModelMorada moradaCli=new ModelMorada();
        model.addAttribute("moradacli",moradaCli);
        if(session.getAttribute("UserLogged") == null){
            return "redirect:/login";
        }
        if(session.getAttribute("carrinho") == null){
            return "redirect:/produto";
        }
        return "selecionarMorada";
    }
    /**
     * POST MAPPING PARA ESTE FORMULARIO
     */
}
