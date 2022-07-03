package org.example.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VerDetalhesEncomendas {
    public void start() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("VerDetalhesListaCompras.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 200);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
