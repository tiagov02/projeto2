package org.example.fx;


import com.example.bd.CRUD.*;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdicionarProdutosListaCompras implements Initializable {

    @FXML
    private TextField labelqtdcomprar;

    @FXML
    private ComboBox<String> select_fornecedor;

    @FXML
    private ComboBox<String> select_produto;

    @FXML
    private TableColumn<EncomendaForLinhaModel, Integer> col_idProd;

    @FXML
    private TableColumn<EncomendaForLinhaModel, String> col_nomeProd;

    @FXML
    private TableColumn<EncomendaForLinhaModel, Integer> col_qtdProduto;

    @FXML
    private TableColumn<EncomendaForLinhaModel, Float> col_valTotal;

    @FXML
    private TableView<EncomendaForLinhaModel> table_lisrProd;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        col_idProd.setCellValueFactory(new PropertyValueFactory<>("idProd"));
        col_nomeProd.setCellValueFactory(new PropertyValueFactory<>("nomeProd"));
        col_qtdProduto.setCellValueFactory(new PropertyValueFactory<>("qtdProd"));
        col_valTotal.setCellValueFactory(new PropertyValueFactory<>("valTotal"));
        for(Fornecedor f:FornecedorCRUD.findFornecedores()){
            select_fornecedor.getItems().add(f.getNome());
        }
        for(Produto p:ProdutoCRUD.findTodosProdutos()){
            select_produto.getItems().add(p.getNome());
        }
    }


    public void backbutton(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "GerentreListaCompras.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void cleanButton(){
        labelqtdcomprar.setText("");
    }

    public void addProdutosFalta(javafx.event.ActionEvent event) {
        int qtdComprar=0;
        boolean isWrong=false;
        Encomendafornecedor ef=new Encomendafornecedor();
        Fornecedor f=FornecedorCRUD.findByName(select_fornecedor.getSelectionModel().getSelectedItem());
        Produto p= ProdutoCRUD.findByName(select_produto.getSelectionModel().getSelectedItem());

        try{
            qtdComprar=Integer.parseInt(labelqtdcomprar.getText());
            isWrong=true;
        }
        catch (NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Não pode introduzir letras nq quantidade do produto");
            dialogoAviso.showAndWait();
            isWrong=false;
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
