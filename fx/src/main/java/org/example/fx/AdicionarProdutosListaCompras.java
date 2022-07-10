package org.example.fx;


import com.example.bd.CRUD.*;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.ModelClasses.EncomendaForLinhaModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
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

    public void addProdutosFalta(javafx.event.ActionEvent event) throws IOException {
        float valTotal=0;
        Encomendafornecedor ef=new Encomendafornecedor();
        Fornecedor f=FornecedorCRUD.findByName(select_fornecedor.getSelectionModel().getSelectedItem());
        Produto p= ProdutoCRUD.findByName(select_produto.getSelectionModel().getSelectedItem());
        ef.setIdfornecedor(f.getIdfornecedor());
        EncomendaFornecedorCRUD.createEncomendaFornecedor(ef);
        for(EncomendaForLinhaModel lef:this.table_lisrProd.getItems()){
            Linhaencomendafornecedor linha=new Linhaencomendafornecedor();
            linha.setValor(new BigDecimal(lef.getValTotal()));
            linha.setNumproduto(lef.getIdProd());
            linha.setQuantidade(lef.getQtdProd());
            linha.setNumencomenda(ef.getNumencomenda());
            valTotal+=lef.getValTotal();
            LinhaEncomendaFornecedorCRUD.createLinhaEncomendaFornecedor(linha);
        }
        ef.setValortotal(new BigDecimal(valTotal));
        try {
            EncomendaFornecedorCRUD.editEncomendaFornecedor(ef);
        } catch (IdNaoEncontradoException e) {
            e.printStackTrace();
        }
        clicaListaCompras(event);
    }

    public void deleteLinhaEncomenda(javafx.event.ActionEvent event){
        if(table_lisrProd.getSelectionModel().getSelectedItem() != null){
            table_lisrProd.getItems().remove(table_lisrProd.getSelectionModel().getSelectedItem());
            initialize(null,null);
        }
    }

    public void addLinhasEncomenda(javafx.event.ActionEvent event){
        int qtd=0;
        float valTotal=0;
        boolean isWrong=false;
        Produto p= ProdutoCRUD.findByName(select_produto.getSelectionModel().getSelectedItem());
        try{
            qtd=Integer.parseInt(labelqtdcomprar.getText());
            isWrong=false;
        }
        catch(NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro!Não pode escrever letras na quantidade a comprar!");
            dialogoAviso.showAndWait();
            isWrong=true;
        }
        if(!isWrong){
            EncomendaForLinhaModel ef=new EncomendaForLinhaModel();
            ef.setIdProd(p.getNumproduto());
            ef.setNomeProd(p.getNome());
            ef.setQtdProd(qtd);
            valTotal=qtd*p.getValorunitario().floatValue();
            ef.setValTotal(valTotal);
            table_lisrProd.getItems().add(ef);
        }
    }
    public void clicaLogout(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "ConfirmacaoSaida.fxml", "Loja Produtos Biológicos", GerenteController.class);
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
    public void clicaDefinicoesFornecedor(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteFornecedores.fxml","Listagem de encomendas",GerenteController.class);
    }
    public void clicaTipoProduto(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"TiposProduto.fxml","Loja Produtos Biológicos",GerenteController.class);
    }
}
