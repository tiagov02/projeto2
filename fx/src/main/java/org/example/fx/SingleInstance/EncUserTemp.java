package org.example.fx.SingleInstance;

import com.example.bd.Entity.Colaborador;

public class EncUserTemp {
    private static EncUserTemp singleinstance;
    private int idEncomenda;

    public static EncUserTemp getInstance(){
        if (singleinstance == null){
            singleinstance = new EncUserTemp();
        }
        return singleinstance;
    }
    //

    public int getCurrentId() {
        return idEncomenda;
    }

    public void setCurrentId(Integer idEncomenda) {
        this.idEncomenda = idEncomenda;
    }
}
