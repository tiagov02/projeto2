package com.example.springwebmvc;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Cliente;
import com.example.springwebmvc.ModelClasses.ModelCliente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ClienteController {

    @GetMapping("/index")
    public String getIndex(){
        return "redirect:/";
    }

    @GetMapping("/meusdetalhes")
    public String getDetalhesCli(HttpSession session, Model model){
        if(session.getAttribute("UserLogged")==null){
            return "redirect:/login";
        }
        Cliente cliTemp=((Cliente) session.getAttribute("UserLogged"));
        ModelCliente cli=new ModelCliente();
        cli.setIdtipocliente(1);
        cli.setCodpostal(cliTemp.getCodpostal());
        cli.setLocalidade(cliTemp.getCodpostaisByCodpostal().getLocalidade());
        cli.setCodpostal(cliTemp.getCodpostal());
        cli.setTelefone(cliTemp.getTelefone());
        cli.setNome(cliTemp.getNome());
        //Retirar
        cli.setPassword(cliTemp.getPassword());
        cli.setIdcliente(cliTemp.getIdcliente());
        cli.setNumporta(cliTemp.getNumporta());
        cli.setRua(cliTemp.getRua());
        cli.setUsername(cliTemp.getUsername());
        String morCli=cli.getRua()+" , "+cli.getNumporta()+" , "+cli.getCodpostal()+" , "+cli.getLocalidade();

        model.addAttribute("cli",cli);
        model.addAttribute("nomeuser",cli.getNome());
        model.addAttribute("morcli",morCli);
        return "meusdetalhes";
    }

    @GetMapping("/alterauser")
    public String getFormAlteraUser(HttpSession session,Model model){
        if(session.getAttribute("UserLogged") == null){
            return "redirect:/login";
        }
        Cliente cliTemp=((Cliente) session.getAttribute("UserLogged"));
        ModelCliente cli=new ModelCliente();
        cli.setIdtipocliente(1);
        cli.setCodpostal(cliTemp.getCodpostal());
        cli.setLocalidade(cliTemp.getCodpostaisByCodpostal().getLocalidade());
        cli.setCodpostal(cliTemp.getCodpostal());
        cli.setTelefone(cliTemp.getTelefone());
        cli.setNome(cliTemp.getNome());
        //Retirar
        cli.setPassword(cliTemp.getPassword());
        cli.setIdcliente(cliTemp.getIdcliente());
        cli.setNumporta(cliTemp.getNumporta());
        cli.setRua(cliTemp.getRua());
        cli.setUsername(cliTemp.getUsername());

        model.addAttribute("cli",cli);
        return "mudarcliente";
    }
    /**
     * CRIAR POSTMAPPING
     */


}
