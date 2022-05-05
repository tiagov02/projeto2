package com.example.bd;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Cliente;

public class Main {
    public static void main(String[] args) {

        byte[] encriptado = new byte[0];
        String encriptadoStr = null;
        try {
            String encripta = "proj2";
            encriptado = Encriptacao.encripta(encripta);
            encriptadoStr=encriptado.toString();
            System.out.println("encriptado: " + encriptadoStr);
            System.out.println("desencriptada: " + Encriptacao.desencripta(encriptado));


        } catch (Exception e) {
            System.out.println("erro +" + e.getMessage());
        }

        Cliente c = new Cliente();
        c.setNumporta(1);
        c.setNome("Clienteeee");
        c.setUsername("clientedanossaapp");
        c.setPassword(encriptadoStr);
        c.setCodpostal("4444-444");
        c.setTelefone("0090");
        c.setNumporta(4);
        c.setRua("rua do cliente");
        c.setIdtipocliente(2);
        ClienteCRUD.createCliente(c);
    }
}
