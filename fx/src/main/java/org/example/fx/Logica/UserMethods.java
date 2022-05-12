package org.example.fx.Logica;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Colaborador;
import org.example.fx.Exceptions.UserPwdErradoaxception;
import org.example.fx.SingleInstance.UserLogado;

import java.util.Arrays;

public class UserMethods {
   public static Colaborador verifyLogin(String username,String pwd) throws UserPwdErradoaxception {
      for(Colaborador c: ColaboradorCRUD.findTodosColaboradores()){
         if(c.getUsername().equals(username) && c.getPassword().equals(pwd)){
            UserLogado.getInstance().setCurrentUser(c);
            return c;
         }
         //
      }
      throw new UserPwdErradoaxception("O utilizador ou a password estão errados;");
   }
}
