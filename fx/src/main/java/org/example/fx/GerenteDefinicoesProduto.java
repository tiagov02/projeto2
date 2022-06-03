package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Colaborador;
import com.example.bd.Entity.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class GerenteDefinicoesProduto implements Initializable {

    @FXML
    private TableColumn<Produto, Integer> numproduto;
    @FXML
    private TableColumn<Produto, String> nomeproduto;
    @FXML
    private TableColumn<Produto, String> tipoProduto;
    @FXML
    private TableColumn<Produto, Integer> qtdStock;
    @FXML
    private TableColumn<Produto, Float> iva;
    @FXML
    private TableColumn<Produto, Float> valorunitario;


    public TableView<Produto> tableProdutos;


    public ObservableList<Produto> getProdutos(){
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        List<Produto> produtoList = ProdutoCRUD.findTodosProdutos();
        produtos.addAll(produtoList);

        return produtos;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Produto prod = new Produto();
        numproduto.setCellValueFactory(new PropertyValueFactory<>("numproduto"));
        nomeproduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipoProduto.setCellValueFactory(new PropertyValueFactory<>("idtipoproduto"));
        qtdStock.setCellValueFactory(new PropertyValueFactory<>("quantidadestock"));
        valorunitario.setCellValueFactory(new PropertyValueFactory<>("valorunitario"));
        iva.setCellValueFactory(new PropertyValueFactory<>("taxaiva"));
        tableProdutos.setItems(getProdutos());
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
