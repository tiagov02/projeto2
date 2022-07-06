package org.example.fx;

import com.example.bd.CRUD.*;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.*;

import java.math.BigDecimal;
import java.util.Date;

public class MainTeste {


    public static void main(String[] args) throws Exception {
        //Codpostais cod=CodPostaisCRUD.findCodPostal("0989-777");
        //System.out.println("Cod postal: "+cod.getCodpostal()+"\nLocalidade : "+cod.getLocalidade());


        Fatura f = new Fatura();
        f.setIdcliente(1);
        f.setIdcolaborador(1);
        f.setIdentrega(1);
        f.setValorfatura(new BigDecimal(2000.12));
        FaturaCRUD.createFatura(f);
    }
}
