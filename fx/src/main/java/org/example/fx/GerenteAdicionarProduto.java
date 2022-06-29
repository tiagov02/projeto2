package org.example.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GerenteAdicionarProduto implements Initializable {
    @FXML
    private ComboBox<String> combotipoproduto;

    @FXML
    private TextField nomeproduto;

    @FXML
    private TextField qtdminima;

    @FXML
    private TextField qtdstock;

    @FXML
    private TextField taxaiva;

    @FXML
    private TextField valorunitario;

    @FXML
    private Button voltarbotao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
