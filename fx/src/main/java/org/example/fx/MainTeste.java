package org.example.fx;

import com.example.bd.CRUD.*;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.*;

import java.math.BigDecimal;

public class MainTeste {


    public static void main(String[] args) throws Exception {
        Codpostais cod=CodPostaisCRUD.findCodPostal("0989-777");
        System.out.println("Cod postal: "+cod.getCodpostal()+"\nLocalidade : "+cod.getLocalidade());
    }
}
