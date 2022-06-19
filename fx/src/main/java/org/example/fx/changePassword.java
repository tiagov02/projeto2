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
        for (Colaborador c : ColaboradorCRUD.findTodosColaboradores()){
            if (!(lb_user.equals(ColaboradorCRUD.findColaboradores(c.getIdcolaborador())))){
                Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                dialogoAviso.setTitle("ERRO!!");
                dialogoAviso.setHeaderText("Não existem colaboradores com tal username");
                dialogoAviso.showAndWait();
                return;
            }
            if (!(lb_pwd.equals(lb_pwd1))){
                Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                dialogoAviso.setTitle("ERRO!!");
                dialogoAviso.setHeaderText("Por favor coloque as palavras-passes iguais.");
                dialogoAviso.showAndWait();
                return;
            }
            if ((lb_user.equals(ColaboradorCRUD.findColaboradores(c.getIdcolaborador())))){
                try {
                    c.setPassword(Encriptacao.encript(lb_pwd.getText()));
                } catch (Exception ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText("Não pode introduzir uma password vazia.");
                    dialogoAviso.showAndWait();
                }
                try {
                    ColaboradorCRUD.editColaborador(c);
                } catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText("ERRO! Não foi possivel atualizar a nova password");
                    dialogoAviso.showAndWait();
                }
            }
        }
    }
}