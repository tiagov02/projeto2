package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Colaborador;

import java.math.BigDecimal;

public class MainTeste {
    public static void main(String[] args) throws Exception {
        /*Colaborador c=new Colaborador();
        c.setCodpostal("4444-444");
        c.setIdtipo(1);
        c.setPassword(Encriptacao.encript("1234567"));
        c.setNome("Nome do Colab");
        c.setNumporta(1);
        c.setRua("Rua ");
        c.setTelefone("5555555");
        c.setSalario(new BigDecimal(555555));
        c.setUsername("nossocolab@nossaempresa.pt");
        ColaboradorCRUD.createColaborador(c);*/
        Colaborador c=ColaboradorCRUD.findColaboradores(8);
        System.out.println("ID DO COLABORADOR: "+c.getUsername());
        System.out.println("Password do colaborador: "+Encriptacao.decrypt(c.getPassword()));
    }
}
