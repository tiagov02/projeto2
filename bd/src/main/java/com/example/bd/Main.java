package com.example.bd;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Cliente;

public class Main {
    public static void main(String[] args) {

        Cliente c1 = new Cliente();
        try {
            c1.setPassword(Encriptacao.encript("teste"));
        } catch (Exception ex){
            System.out.println("teste");
        }

    }
}
