package org.example.fx;

import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ColaboradorAtualizaStocksController implements Initializable {
    @FXML
    private TableColumn<Produto, Integer> tbl_QtdStock;

    @FXML
    private TableColumn<Produto, Integer> tbl_qtdMinima;

    @FXML
    private TableColumn<Produto, BigDecimal> tbl_valUnitario;

    @FXML
    private TableView<Produto> table_produtos;

    @FXML
    private TableColumn<Produto, String> tbl_nomeProd;

    @FXML
    private TableColumn<Produto, Integer> tbl_tipoProduto;

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "ColaboradorMenuPrincipal.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
        //Cliente[] clientes = listarCliMaisGasto();
        //VAI IMPLEMENTAR INICIALIZABLE
    }

    public void clicaListaCompras(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaCompras.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }

    public void clicaListaEncomendas(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaEncomendas.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }

    public void clicaAtualizaStocks(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorAtualizaStocks.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbl_nomeProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbl_tipoProduto.setCellValueFactory(new PropertyValueFactory<>("idtipoproduto"));
        tbl_QtdStock.setCellValueFactory(new PropertyValueFactory<>("quantidadestock"));
        tbl_qtdMinima.setCellValueFactory(new PropertyValueFactory<>("quantidademinima"));
        tbl_valUnitario.setCellValueFactory(new PropertyValueFactory<>("valorunitariototal"));

        for(Produto p: ProdutoCRUD.findTodosProdutos()){
            table_produtos.getItems().add(p);
        }
    }
}
