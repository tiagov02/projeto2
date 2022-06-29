package org.example.fx;


import com.example.bd.CRUD.*;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
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
        int idForn=0,idProd=0,qtdComprar=0;
        Encomendafornecedor ef=new Encomendafornecedor();
        for(Fornecedor f:FornecedorCRUD.findFornecedores()){
            if(f.getNome().equals(select_fornecedor.getSelectionModel().getSelectedItem())){
                idForn=f.getIdfornecedor();
            }
        }
        for(Produto p:ProdutoCRUD.findTodosProdutos()){
            if(p.getNome().equals(select_produto.getSelectionModel().getSelectedItem())){
                idProd=p.getNumproduto();
            }
        }
        try{
            qtdComprar=Integer.parseInt(labelqtdcomprar.getText());
        }
        catch (NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Não pode introduzir letras nq quantidade do produto");
            dialogoAviso.showAndWait();
        }
        float aux=((ProdutoCRUD.findProduto(idProd)).getValorunitario().floatValue() * qtdComprar);
        ef.setIdfornecedor(idForn);
        ef.setQuantidade(qtdComprar);
        ef.setValortotal(new BigDecimal(Float.toString(aux)));
        EncomendaFornecedorCRUD.createEncomendaFornecedor(ef);
        Linhaencomendafornecedor lf=new Linhaencomendafornecedor();
        lf.setNumencomenda(ef.getNumencomenda());
        lf.setQuantidade(qtdComprar);
        lf.setNumproduto(idProd);
        lf.setValor(new BigDecimal(Float.toString(aux)));
        LinhaEncomendaFornecedorCRUD.createLinhaEncomendaFornecedor(lf);
        Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
        dialogoAviso.setTitle("SUCESSO!!");
        dialogoAviso.setHeaderText("Adicionou um novo produto com sucesso!!");
        dialogoAviso.showAndWait();
        cleanButton();
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
