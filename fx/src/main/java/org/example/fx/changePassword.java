package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Colaborador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.security.spec.ECField;

public class changePassword {
    @FXML
    private PasswordField lb_pwd;
    @FXML
    private TextField lb_user;
    @FXML
    private PasswordField lb_pwd1;
    @FXML
    private Button buttonalterar;
    @FXML
    private Button buttonlimpar;
    @FXML
    private Button buttonvoltar;

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "login.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void limparDados(){
        lb_pwd.setText("");
        lb_pwd1.setText("");
        lb_user.setText("");
    }

    public void alterarPassword(){
        int cont=0;
        Colaborador col=null;
        for (Colaborador c : ColaboradorCRUD.findTodosColaboradores()){
            if(this.lb_pwd.getText().equals(c.getUsername())){
                cont++;
                col=c;
            }
        }
        if(cont==0){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!");
            dialogoAviso.setHeaderText("Esse user não existe!!");
            dialogoAviso.showAndWait();
        }
        try {
            col.setPassword(Encriptacao.encript(lb_pwd.getText()));
        }
        catch (Exception ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!");
            dialogoAviso.setHeaderText("Erro no sistema!!Pf tente mais tarde ou contacte os Serviços");
            dialogoAviso.showAndWait();
        }
    }
}