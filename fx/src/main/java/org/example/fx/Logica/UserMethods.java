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
      for(Colaborador c: ColaboradorCRUD.findTodosColaboradores()){
         try{
            if(c.getUsername().equals(username) && c.getPassword().equals(Encriptacao.encript(pwd))){
               UserAtual.getInstance().setCurrentUser(c);
               return c;
            }
         }catch (Exception e){
            throw new RunTimeError("Ocorreu um erro no programa, por favor tente de novo dentro de alguns minutos");
         }
      }
      throw new UserPwdErradoaxception("O utilizador ou a password estão errados;");
   }
}
