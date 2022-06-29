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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
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
    @FXML
    public TableView<ProdutoTipo> tableproduto;
    @FXML
    private Button btn_pesq;
    @FXML
    private TextField lbl_codBarras;


    public void updateNomeProduto(){
        nomeproduto.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        nomeproduto.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProdutoTipo, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProdutoTipo, String> produtoTipoStringCellEditEvent) {
                ProdutoTipo prod = produtoTipoStringCellEditEvent.getRowValue();
                prod.setNome(produtoTipoStringCellEditEvent.getNewValue());
                Produto p=ProdutoCRUD.findProduto(prod.getId());
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
        });
    }

    public void updateQtdStock(){
        qtdstock.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        qtdstock.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProdutoTipo, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProdutoTipo, Integer> produtoTipoIntegerCellEditEvent) {
                ProdutoTipo prod = produtoTipoIntegerCellEditEvent.getRowValue();
                prod.setQtdStock(produtoTipoIntegerCellEditEvent.getNewValue());
                Produto p=ProdutoCRUD.findProduto(prod.getId());
                    if (prod.getId() == p.getNumproduto()){
                        p.setQuantidadestock(prod.getQtdStock());
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

    public void updateQtdMinima(){
        qtdminima.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        qtdminima.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProdutoTipo, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProdutoTipo, Integer> produtoTipoIntegerCellEditEvent) {
                ProdutoTipo prod = produtoTipoIntegerCellEditEvent.getRowValue();
                prod.setQtdMinima(produtoTipoIntegerCellEditEvent.getNewValue());
                    Produto p=ProdutoCRUD.findProduto(prod.getId());
                        p.setQuantidademinima(prod.getQtdMinima());
                        try {
                            ProdutoCRUD.editProduto(p);
                        } catch (IdNaoEncontradoException ex){
                            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                            dialogoAviso.setTitle("ERRO!!");
                            dialogoAviso.setHeaderText(ex.getMessage());
                            dialogoAviso.showAndWait();
                        }
                    }
        });
    }

    public void updateValorUnitario(){
        valor.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        valor.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProdutoTipo, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ProdutoTipo, Float> produtoTipoFloatCellEditEvent) {
                ProdutoTipo prod = produtoTipoFloatCellEditEvent.getRowValue();
                prod.setValUnit(produtoTipoFloatCellEditEvent.getNewValue());
                Produto p = ProdutoCRUD.findProduto(prod.getId());
                p.setValorunitariototal(new BigDecimal(prod.getValUnit()));
                try {
                    ProdutoCRUD.editProduto(p);
                } catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText(ex.getMessage());
                    dialogoAviso.showAndWait();
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableproduto.setEditable(true);
        updateNomeProduto();
        updateQtdStock();
        updateQtdMinima();
        updateValorUnitario();
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

    public void pesquisa(javafx.event.ActionEvent event){
        int codBarras=0;

        if (lbl_codBarras.getText().equals("")){
            initialize(null, null);
            return;
        }
        try{
            codBarras=Integer.parseInt(lbl_codBarras.getText());
        }
        catch(NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Não pode introduzir letras no cod de barras!!");
            dialogoAviso.showAndWait();
        }
        Produto p=ProdutoCRUD.findProduto(codBarras);
        if(p==null){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Não existe nenhum produto com esse cod de barras");
            dialogoAviso.showAndWait();
        }
        else{
            tableproduto.setItems(FXCollections.observableArrayList());
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
    public void ClickAddProduto(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event,"AdicionarProduto.fxml","Loja Produtos Biologicos",GerenteAdicionarProduto.class);
    }
    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "GerenteMenuPrincipal.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ConfirmacaoSaida.fxml", "Loja Produtos Biológicos", GerenteController.class);
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
