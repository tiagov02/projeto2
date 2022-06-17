package org.example.fx;


import com.example.bd.CRUD.*;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class AdicionarProdutosListaCompras {

    @FXML
    private TextField labelnumproduto;
    @FXML
    private TextField labelqtdcomprar;
    @FXML
    private ComboBox<Fornecedor> comboBox;




    public void backbutton(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "GerentreListaCompras.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void cleanButton(){
        labelnumproduto.setText("");
        labelqtdcomprar.setText("");
    }

    public void addProdutosFalta() {
        int numEncomenda = 0, quantidade = 0, cont = 0;
        for (Produto p : ProdutoCRUD.findTodosProdutos()) {
            if (labelnumproduto.getText().equals(Integer.toString(p.getNumproduto()))) {
                cont++;
                //numEncomenda = (EncomendaFornecedorCRUD.findTodasEncomendasFornecedores().size()) + 1;
                Encomendafornecedor enc = new Encomendafornecedor();
                Fornecedor fornecedor = comboBox.getValue();
                //enc.setNumencomenda(numEncomenda);
                enc.setIdfornecedor(fornecedor.getIdfornecedor());
                enc.setQuantidade(Integer.parseInt(labelqtdcomprar.getText()));
                //enc.setQuantidade();
            }
        }
        if (cont==0){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!");
            dialogoAviso.setHeaderText("Erro! O número de produto não existe!");
            dialogoAviso.showAndWait();
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
