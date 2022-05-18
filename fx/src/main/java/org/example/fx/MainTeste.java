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
        c1.setNome("colaborador");
        c1.setTelefone("999999999");
        c1.setSalario(new BigDecimal(1500.5));
        c1.setNumporta(456);
        c1.setRua("colaborador");
        c1.setPassword(Encriptacao.encript("colaborador"));
        c1.setUsername("colaborador");
        c1.setIdtipo(1);
        ColaboradorCRUD.createColaborador(c1);

    }
}
