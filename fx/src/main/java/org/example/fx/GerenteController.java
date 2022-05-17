package org.example.fx;

import com.example.bd.CRUD.EncomendaFornecedorCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Encomendafornecedor;
import com.example.bd.Entity.Fornecedor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;

public class GerenteController{
    @FXML
    private Button botaoAtualizaStocks;

    @FXML
    private Button botaoDefinicoesClientes;

    @FXML
    private Button botaoDefinicoesColaborador;

    @FXML
    private Button botaoDefinicoesProdutos;

    @FXML
    private Button botaoListaCompras;

    @FXML
    private Button botaoListaEncomendas;

    @FXML
    private Button btn_procura;

    @FXML
    private TableColumn<Encomendafornecedor, String> fornecedorField;

    @FXML
    private TableColumn<Encomendafornecedor, String> estadoField;

    @FXML
    private TableColumn<Encomendafornecedor, String> moradaField;

    @FXML
    private TableColumn<Encomendafornecedor, String> telefoneField;

    @FXML
    private TableColumn<Encomendafornecedor, BigDecimal> valorTotalField;

    @FXML
    private TableView<Encomendafornecedor> table;


    public void ClicaListarEncomendas(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Listagem de encomendas",GerenteController.class);
        ListaEncomendas(event);
    }

    public void ListaEncomendas(javafx.event.ActionEvent event){
        fornecedorField.setCellValueFactory(new PropertyValueFactory<>("fornecedorByIdfornecedor.getNome()"));
        //estadoField.setCellValueFactory(new PropertyValueFactory<>("estado"));
        moradaField.setCellValueFactory(new PropertyValueFactory<>("morada"));
        telefoneField.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        valorTotalField.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        //table.getItems().setAll(EncomendaFornecedorCRUD.findTodasEncomendasFornecedores());
        for(Encomendafornecedor e:EncomendaFornecedorCRUD.findTodasEncomendasFornecedores()){
            table.getItems().add(e);
        }
    }

}
