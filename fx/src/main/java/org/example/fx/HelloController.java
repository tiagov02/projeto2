package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    private Button BtnInicio;

    @FXML
    protected void onHelloButtonClick() {
        System.out.println(ClienteCRUD.findClientesTodos());
        welcomeText.setText("Welcome to JavaFX Application!");
    }

}