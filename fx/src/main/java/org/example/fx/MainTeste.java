package org.example.fx;

import com.example.bd.CRUD.*;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.*;

import java.math.BigDecimal;

public class MainTeste {


    public static void main(String[] args) throws Exception {
        int quantidade, qtd = 0;
        /*
        for (Linhaencomendafornecedor linha : LinhaEncomendaFornecedorCRUD.findAllLinhasEncomendasFornecedores()){
            System.out.println(linha.getEncomendafornecedorByNumencomenda().getNumencomenda()); //NUMEROS DA LINHA ENCOMENDA FORNECEDOR
        }


        for (Encomendafornecedor enc : EncomendaFornecedorCRUD.findTodasEncomendasFornecedores()){
            System.out.println(enc.getNumencomenda()); //NUMEROS DE ENCOMENDAS
        }
        */

        for (Linhaencomendafornecedor linha : LinhaEncomendaFornecedorCRUD.findAllLinhasEncomendasFornecedores()){
            for (Encomendafornecedor enc : EncomendaFornecedorCRUD.findTodasEncomendasFornecedores()){
                if (linha.getEncomendafornecedorByNumencomenda().getNumencomenda() == enc.getNumencomenda()){
                    System.out.println(linha.getEncomendafornecedorByNumencomenda().getNumencomenda() == enc.getNumencomenda());
                    System.out.println(linha.getEncomendafornecedorByNumencomenda().getNumencomenda());
                }

            }

        }

    }
}
