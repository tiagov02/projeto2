package org.example.fx;

import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.Entity.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class GerenteListaEncomendas implements Initializable{

    @FXML
    private Button botaoAtualizaStocks;

    @FXML
    private Button botaoListaCompras;

    @FXML
    private Button botaoListarEncomendas;

    @FXML
    private Button botaoPaginaPrincipal;

    @FXML
    private Button btn_procura;

    @FXML
    private TableColumn<Fatura, Float> NumFatura;

    @FXML
    private TableColumn<Fatura, String> clienteField;

    @FXML
    private TableColumn<Fatura, String> estadoField;

    @FXML
    private TableColumn<Fatura, String> moradaField;

    @FXML
    private TableView<Fatura> table;

    @FXML
    private TableColumn<Fatura, String> telefoneField;

    @FXML
    private TableColumn<Fatura, Float> valorTotalField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        }
    }
}
