package org.example.fx;

import com.example.bd.CRUD.CodPostaisCRUD;
import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Codpostais;
import com.example.bd.Entity.Colaborador;

import java.math.BigDecimal;

public class MainTeste {
    public static void main(String[] args) throws Exception {
        Colaborador c1 = new Colaborador();
        c1.setCodpostal("4444-444");
        c1.setNome("gerente");
        c1.setTelefone("999999999");
        c1.setSalario(new BigDecimal(1000.5));
        c1.setNumporta(123);
        c1.setRua("gerente");
        c1.setPassword("gerente");
        c1.setUsername("gerente");
        c1.setIdtipo(2);
        ColaboradorCRUD.createColaborador(c1);
    }
}
