package org.example.fx;

import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Produto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerenteAtualizaStocks implements Initializable {

    @FXML
    private TableColumn<ProdutoTipo, Integer> numproduto;
    @FXML
    private TableColumn<ProdutoTipo, String> nomeproduto;
    @FXML
    private TableColumn<ProdutoTipo, Integer> tipoproduto;
    @FXML
    private TableColumn<ProdutoTipo, Integer> qtdstock;
    @FXML
    private TableColumn<ProdutoTipo, Integer> qtdminima;
    @FXML
    private TableColumn<ProdutoTipo, Float> valor;


    public TableView<ProdutoTipo> tableproduto;

    public ObservableList<Produto> getProdutos(){
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        List<Produto> produtoList = ProdutoCRUD.findTodosProdutos();
        produtos.addAll(produtoList);

        return produtos;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numproduto.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeproduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipoproduto.setCellValueFactory(new PropertyValueFactory<>("tipoProduto"));
        qtdstock.setCellValueFactory(new PropertyValueFactory<>("qtdStock"));
        qtdminima.setCellValueFactory(new PropertyValueFactory<>("qtdMinima"));
        valor.setCellValueFactory(new PropertyValueFactory<>("valUnit"));
        ProdutoTipo pt= new ProdutoTipo();
        for(Produto p:ProdutoCRUD.findTodosProdutos()){
            pt.setId(p.getNumproduto());
            pt.setNome(p.getNome());
            pt.setQtdStock(p.getQuantidadestock());
            pt.setQtdMinima(p.getQuantidademinima());
            pt.setValUnit(p.getValorunitariototal().floatValue());
            pt.setTipoProduto(p.getTipoprodutoByIdtipoproduto().getSeccao());
            tableproduto.getItems().add(pt);
        }
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
