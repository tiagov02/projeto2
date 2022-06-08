package org.example.fx;

import com.example.bd.CRUD.CodPostaisCRUD;
import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Codpostais;
import com.example.bd.Entity.Colaborador;
import com.example.bd.Entity.Fatura;
import com.example.bd.Entity.Produto;

import java.math.BigDecimal;

public class MainTeste {
    public static void main(String[] args) throws Exception {

        Produto prod=ProdutoCRUD.findProduto(50);
        System.out.println(prod.getNome());



    }
}
