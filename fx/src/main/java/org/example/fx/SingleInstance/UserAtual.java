package org.example.fx.SingleInstance;

import com.example.bd.Entity.Colaborador;

public class UserAtual {
        private static UserAtual singleinstance;
        private Colaborador currentUser;

        public static UserAtual getInstance(){
            if (singleinstance == null){
                singleinstance = new UserAtual();
            }
            return singleinstance;
        }
        //

        public Colaborador getCurrentUser() {
            return currentUser;
        }

        public void setCurrentUser(Colaborador currentUser) {
            this.currentUser = currentUser;
        }
}
