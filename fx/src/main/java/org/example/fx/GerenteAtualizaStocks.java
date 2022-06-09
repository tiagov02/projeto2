package org.example.fx;

import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Produto;
import com.example.bd.Entity.Tipoproduto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
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


    public void updateProdutos(){
        tableproduto.setEditable(true);
        nomeproduto.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        nomeproduto.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProdutoTipo, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProdutoTipo, String> produtoTipoStringCellEditEvent) {
                for(Produto p:ProdutoCRUD.findTodosProdutos()){
                    ProdutoTipo prod = produtoTipoStringCellEditEvent.getRowValue();
                    prod.setNome(produtoTipoStringCellEditEvent.getNewValue());
                    p.setNome(prod.getNome());
                    try {
                        ProdutoCRUD.editProduto(p);
                    } catch (IdNaoEncontradoException ex){
                        Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                        dialogoAviso.setTitle("ERRO!!");
                        dialogoAviso.setHeaderText(ex.getMessage());
                        dialogoAviso.showAndWait();
                    }
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateProdutos();
        numproduto.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeproduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipoproduto.setCellValueFactory(new PropertyValueFactory<>("tipoProduto"));
        qtdstock.setCellValueFactory(new PropertyValueFactory<>("qtdStock"));
        qtdminima.setCellValueFactory(new PropertyValueFactory<>("qtdMinima"));
        valor.setCellValueFactory(new PropertyValueFactory<>("valUnit"));
        for(Produto p:ProdutoCRUD.findTodosProdutos()){
            ProdutoTipo pt= new ProdutoTipo();
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

    public void clicaDefinicoesClientes(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void ClicaListarEncomendas(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Listagem de encomendas",GerenteController.class);
    }
}
