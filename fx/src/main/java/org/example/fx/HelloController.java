package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println(ClienteCRUD.findClientesTodos());
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}