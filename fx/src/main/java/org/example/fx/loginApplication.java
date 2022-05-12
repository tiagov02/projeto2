package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Colaborador;
import org.example.fx.Exceptions.UserPwdErradoaxception;

public class loginApplication {
   public Colaborador verifyLogin(String username,byte[] pwd) throws UserPwdErradoaxception {
      for(Colaborador c: ColaboradorCRUD.findTodosColaboradores()){
         if(c.getUsername().equals(username) && c.getPassword().equals(pwd.toString())){
            return c;
         }
      }
      throw new UserPwdErradoaxception("O utilizador ou a password estão errados;");
   }
}
