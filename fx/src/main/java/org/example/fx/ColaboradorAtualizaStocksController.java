package org.example.fx;

import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class ColaboradorAtualizaStocksController implements Initializable {

    @FXML
    private TextField lbl_nomeProd;

    @FXML
    private TextField lbl_qtdMinima;

    @FXML
    private TextField lbl_qtdStock;

    @FXML
    private TextField lbl_valUnitario;

    @FXML
    private ComboBox<?> sel_tipoProd;

    @FXML
    private Button OKButton;
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

    @FXML
    private Button btn_pesq;

    @FXML
    private TextField lbl_codBarras;

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "ColaboradorMenuPrincipal.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
        //Cliente[] clientes = listarCliMaisGasto();
        //VAI IMPLEMENTAR INICIALIZABLE
    }


    public void clicaListaCompras(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaCompras.fxml", "Loja Produtos Biológicos", ColaboradorListaComprasController.class);
    }

    public void clicaListaEncomendas(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaEncomendas.fxml", "Loja Produtos Biológicos", ColaboradorListaEncomendasController.class);
    }

    public void clicaAtualizaStocks(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorAtualizaStocks.fxml", "Loja Produtos Biológicos", ColaboradorAtualizaStocksController.class);
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

    public void clickOK(javafx.event.ActionEvent event){
        Produto pro=table_produtos.getSelectionModel().getSelectedItem();
        lbl_nomeProd.setText(pro.getNome());
        lbl_qtdMinima.setText(pro.getQuantidademinima().toString());
        lbl_qtdStock.setText(pro.getQuantidadestock().toString());
        lbl_valUnitario.setText(pro.getValorunitario().toString());
    }
}
