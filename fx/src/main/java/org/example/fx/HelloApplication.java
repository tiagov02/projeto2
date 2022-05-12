package org.example.fx;

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
import org.example.fx.Logica.UserMethods;

import java.io.IOException;

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

    public void ClicaLogin() {
        String pwd=lb_pwd.getText();
        String user=lb_user.getText();
        //este campo encripta antes de ir para a verificação do login
        try{
            Colaborador c= UserMethods.verifyLogin(user,pwd);
            System.out.println("o user existe: "+c.getUsername());
        }
        catch (UserPwdErradoaxception ex){
            //Apresentação da mensagem
        }
        //
    }

    public static void main(String[] args) {
        launch();
    }
}