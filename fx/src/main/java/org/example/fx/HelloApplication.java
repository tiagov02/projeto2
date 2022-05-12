package org.example.fx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 755, 537);
        stage.setTitle("Loja Produtos Biológicos ");
        stage.setScene(scene);
        stage.show();
        //loginApplication.verifyLogin()
    }

    public static void main(String[] args) {
        launch();
    }
}