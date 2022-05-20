package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.EncomendaFornecedorCRUD;
import com.example.bd.CRUD.FornecedorCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.*;
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
import java.util.ArrayList;
import java.util.List;

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


/*
    //LISTAR ENCOMENDAS
    @FXML
    private TableColumn<Fatura, Integer> NumEncomenda;
    @FXML
    private TableColumn<Fatura, String> ClienteField;
    @FXML
    private TableColumn<Moradaentrega, String> moradaField;
    @FXML
    private TableColumn<Cliente, String> telefoneField;
    @FXML
    private TableColumn<Fatura, BigDecimal> valorTotalField;

 */



    @FXML
    private TableView<Fornecedor> tableClientes;
    @FXML
    private TableView<Fornecedor> tableListarEncomendas;

    //DEFINICOES DE COLABORADOR
    @FXML
    private TableColumn<Colaborador, Integer> colNumero;
    @FXML
    private TableColumn<Colaborador, String> colNome;
    @FXML
    private TableColumn<Colaborador, String> colTelefone;
    @FXML
    private TableColumn<Colaborador, BigDecimal> colSalario;
    @FXML
    private TableColumn<Colaborador, Integer> colEstado;

    public TableView<Colaborador> tableColaborador;


    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerenteMenuPrincipal.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaListaCompras(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentreListaCompras.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaAtualizaStockProdutos(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerenteAtualizaStocks.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaDefinicoesColaborador(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesColaborador.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }
    public void clicaDefinicoesProdutos(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesProdutos.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaDefinicoesClientes(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void ClicaListarEncomendas(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Listagem de encomendas",GerenteController.class);
    }

    public void listaEncomendas(javafx.event.ActionEvent event){
    }

    public void gerenteDefinicoesCliente(List<Cliente> cli){
        ObservableList<Cliente> clienteObservableList = FXCollections.observableList(cli);
        TableColumn id = new TableColumn("#");
        TableColumn nomeCliente = new TableColumn("Nome");
        TableColumn tipoCliente = new TableColumn("Tipo Cliente");
        TableColumn telefone = new TableColumn("Telefone");
        TableColumn morada = new TableColumn("Morada");
        TableColumn codPostal = new TableColumn("Cod Postal");
        tableListarEncomendas.getColumns().addAll(id, nomeCliente, tipoCliente, telefone, morada, codPostal);


        id.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idcliente"));
        nomeCliente.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
        tipoCliente.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idtipocliente"));
        telefone.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));
        morada.setCellValueFactory(new PropertyValueFactory<Cliente, String>("codpostal"));
        codPostal.setCellValueFactory(new PropertyValueFactory<Cliente, String>("codpostal"));

        //tableClientes.setItems(clienteObservableList);
    }



}
