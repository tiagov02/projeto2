package org.example.fx;

import com.example.bd.CRUD.*;
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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerenteListaCompras implements Initializable {


    @FXML
    private TableColumn<ListaComprasClass, Integer> colNumero;
    @FXML
    private TableColumn<ListaComprasClass, String> colProduto;
    @FXML
    private TableColumn<ListaComprasClass, String> colTipoProduto;
    @FXML
    private TableColumn<ListaComprasClass, Integer> colQtdExistente;
    @FXML
    private TableColumn<ListaComprasClass, Integer> colQtdComprar;

    public TableView<ListaComprasClass> tableListaCompras;

    public ObservableList<Produto> getProdutos(){
        ObservableList<Produto> produtos = FXCollections.observableArrayList();
        List<Produto> produtoList = ProdutoCRUD.findTodosProdutos();
        produtos.addAll(produtoList);

        return produtos;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colNumero.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colTipoProduto.setCellValueFactory(new PropertyValueFactory<>("tipoProduto"));
        colQtdExistente.setCellValueFactory(new PropertyValueFactory<>("qtdExistente"));
        colQtdComprar.setCellValueFactory(new PropertyValueFactory<>("qtdComprar"));
        ListaComprasClass list= new ListaComprasClass();
        for(Linhaencomendafornecedor l: LinhaEncomendaFornecedorCRUD.findAllLinhasEncomendasFornecedores()){
            list.setId(l.getNumencomenda());
            list.setProduto(l.getProdutoByNumproduto().getNome());
            list.setQtdComprar(l.getQuantidade());
            list.setQtdExistente(l.getProdutoByNumproduto().getQuantidadestock());
            list.setTipoProduto(l.getProdutoByNumproduto().getTipoprodutoByIdtipoproduto().getSeccao());
            tableListaCompras.getItems().add(list);
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
