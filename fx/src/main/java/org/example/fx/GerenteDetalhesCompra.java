package org.example.fx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.fx.ModelClasses.ModelLinhaFatura;

import java.net.URL;
import java.util.ResourceBundle;

public class GerenteDetalhesCompra implements Initializable {
    @FXML
    private TableColumn<ModelLinhaFatura, Integer> colNumero;

    @FXML
    private TableColumn<ModelLinhaFatura, Float> colPreco;

    @FXML
    private TableColumn<ModelLinhaFatura, String> colProduto;

    @FXML
    private TableColumn<ModelLinhaFatura, Integer> colQtd;

    @FXML
    private TableColumn<ModelLinhaFatura, String> colTipoProduto;

    @FXML
    private Label moradaEnc;

    @FXML
    private Label nomeCliente;

    @FXML
    private TableView<ModelLinhaFatura> tableListaCompras;

    @FXML
    private Label telefonecliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
