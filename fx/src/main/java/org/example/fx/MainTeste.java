package org.example.fx;

import com.example.bd.CRUD.*;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.*;

import java.math.BigDecimal;

public class MainTeste {


    public static void main(String[] args) throws Exception {
        Cliente cli =  new Cliente();
        cli.setIdtipocliente(1);
        cli.setCodpostal("4444-444");
        cli.setNome("cliente");
        cli.setTelefone("239842384");
        cli.setNumporta(1234);
        cli.setRua("slçdfksdf");
        cli.setPassword(Encriptacao.encript("cliente"));
        cli.setUsername("cliente");
        ClienteCRUD.createCliente(cli);
    }
}
