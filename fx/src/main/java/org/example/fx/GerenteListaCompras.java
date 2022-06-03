package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.EncomendaFornecedorCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.CRUD.TipoProdutoCRUD;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerenteListaCompras implements Initializable {


    @FXML
    private TableColumn<Produto, Integer> colNumero;
    @FXML
    private TableColumn<Produto, String> colProduto;
    @FXML
    private TableColumn<Tipoproduto, String> colTipoProduto;
    @FXML
    private TableColumn<Produto, Integer> colQtdExistente;
    //@FXML
    //private TableColumn<Linhaencomendafornecedor, Integer> colQtdComprar;


    public TableView<Produto> tableListaCompras;


    public ObservableList<Produto> getProdutos(){
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        List<Produto> produtoList = ProdutoCRUD.findTodosProdutos();
        produtos.addAll(produtoList);

        return produtos;
    }

    public ObservableList<Tipoproduto> getTipoProduto(){
        ObservableList<Tipoproduto> tipoprodutos = FXCollections.observableArrayList();
        List<Tipoproduto> tipoprodutoList = TipoProdutoCRUD.findTiposProduto();
        tipoprodutos.addAll(tipoprodutoList);

        return tipoprodutos;
    }

    public ObservableList<Encomendafornecedor> getEncomendaFornecedor(){
        ObservableList<Encomendafornecedor> encomendafornecedors = FXCollections.observableArrayList();
        List<Encomendafornecedor> encomendafornecedorList = EncomendaFornecedorCRUD.findTodasEncomendasFornecedores();
        encomendafornecedors.addAll(encomendafornecedorList);

        return encomendafornecedors;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numproduto"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTipoProduto.setCellValueFactory(new PropertyValueFactory<>("seccao"));
        colQtdExistente.setCellValueFactory(new PropertyValueFactory<>("quantidadestock"));
        //colQtdComprar.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tableListaCompras.setItems(getProdutos());
    }

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
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
}
