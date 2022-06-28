package org.example.fx.Logica;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Colaborador;
import org.example.fx.Exceptions.RunTimeError;
import org.example.fx.Exceptions.UserPwdErradoaxception;
import org.example.fx.SingleInstance.UserAtual;

import java.util.Arrays;

public class UserMethods {
   public static Colaborador verifyLogin(String username,String pwd) throws UserPwdErradoaxception, RunTimeError {
      String pass;
      try{
         pass=Encriptacao.encript(pwd);
      }
      catch(Exception e){
         throw new RunTimeError("Ocorreu um erro no programa, por favor tente de novo dentro de alguns minutos");
      }
      Colaborador c=ColaboradorCRUD.loginColab(username,pass);

      if(c==null){
         throw new UserPwdErradoaxception("O utilizador ou a password estão errados;");
      }
      return c;
   }
}
