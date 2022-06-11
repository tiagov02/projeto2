package org.example.fx;


import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.FornecedorCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.util.List;

public class AdicionarProdutosListaCompras {

    @FXML
    private TextField labelnumproduto;
    @FXML
    private TextField labelqtdcomprar;
    @FXML
    private ComboBox<Tipoproduto> comboBox;

    //



    public void backbutton(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "GerentreListaCompras.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void cleanButton(){
        labelnumproduto.setText("");
        labelqtdcomprar.setText("");
    }

    public void addProdutosFalta(){
        for (Produto p : ProdutoCRUD.findTodosProdutos()){
            if (!(labelnumproduto.getText().equals(p.getNumproduto()))){
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("ERRO!");
                dialogoAviso.setHeaderText("Erro! O número de produto não existe!");
                dialogoAviso.showAndWait();
            }
            if (labelnumproduto.getText().equals(p.getNumproduto())){
                //ENTAO VOU ADICIONAR UMA NOVA LINHAENCOMENDAFORNECEDOR E VOU ADICIONAR UMA NOVA ENCOMENDAFORNECEDOR
            }
        }
    }


    public void clicaadicionarProduto(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "AdicionarProduto.fxml", "Loja Produtos Biológicos", GerenteController.class);
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
