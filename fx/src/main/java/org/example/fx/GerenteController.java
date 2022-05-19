package org.example.fx;

import com.example.bd.CRUD.EncomendaFornecedorCRUD;
import com.example.bd.CRUD.FornecedorCRUD;
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
import javafx.scene.control.Label;
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
    private TableColumn<Fornecedor, String> fornecedorField;

    @FXML
    private TableColumn<Fornecedor, String> estadoField;

    @FXML
    private TableColumn<Encomendafornecedor, String> moradaField;

    @FXML
    private TableColumn<Fornecedor, String> telefoneField;

    @FXML
    private TableColumn<Fornecedor, BigDecimal> valorTotalField;

    @FXML
    private TableView<Fornecedor> table;


    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerenteMenuPrincipal.fxml", "Pagina Principal", GerenteController.class);
    }

    public void clicaListaCompras(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentreListaCompras.fxml", "Lista Compras", GerenteController.class);
    }

    public void clicaAtualizaStockProdutos(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerenteAtualizaStocks.fxml", "Atualiza Stocks", GerenteController.class);
    }

    public void clicaDefinicoesColaborador(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesColaborador.fxml", "Definicoes Colaborador", GerenteController.class);
    }
    public void clicaDefinicoesProdutos(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesProdutos.fxml", "Definicoes Produtos", GerenteController.class);
    }

    public void clicaDefinicoesClientes(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesClientes.fxml", "Definicoes Clientes", GerenteController.class);
    }

    public void ClicaListarEncomendas(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Listagem de encomendas",GerenteController.class);
        ListaEncomendas(event);
    }

    public void ListaEncomendas(javafx.event.ActionEvent event){
    }


}
