package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Colaborador;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import org.example.fx.Exceptions.UserPwdErradoaxception;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.Logica.UserMethods;

import java.io.IOException;
import java.math.BigDecimal;

public class HelloApplication extends Application {
    @FXML
    private Button Btniniciar;
    @FXML
    private PasswordField lb_pwd;
    @FXML
    private TextField lb_user;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 755, 537);
        stage.setTitle("Loja Produtos Biológicos ");
        stage.setScene(scene);
        stage.show();
        //loginApplication.verifyLogin()
    }

    public void ClicaLogin(javafx.event.ActionEvent event) throws Exception {
        String pwd=lb_pwd.getText();
        String user= lb_user.getText();
        try{
            Colaborador c= UserMethods.verifyLogin(user,pwd);
            TrocaPaineis.changePanel(event,"menuPrincipalGerente.fxml","Menu do Gerente",ColaboradorController.class);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        launch();
    }
}