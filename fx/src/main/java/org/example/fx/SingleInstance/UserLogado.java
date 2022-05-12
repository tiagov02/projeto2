package org.example.fx.SingleInstance;

import com.example.bd.Entity.Colaborador;

public class UserLogado {
    private static UserLogado singleinstance;
    private Colaborador currentUser;

    public static UserLogado getInstance(){
        if (singleinstance == null){
            singleinstance = new UserLogado();
        }
        return singleinstance;
    }

    public Colaborador getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Colaborador currentUser) {
        this.currentUser = currentUser;
    }
}
