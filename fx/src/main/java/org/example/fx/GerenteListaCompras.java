package org.example.fx;

import com.example.bd.CRUD.*;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.example.fx.Exceptions.NumberException;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
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

    @FXML
    private TextField procuraproduto;
    @FXML
    private Button buttonpesquisa;
    @FXML
    private Button buttonaddproduto;
    @FXML
    private TextField numeroProduto;
    @FXML
    private Button buttonLimpar;
    @FXML
    private Button buttonRemover;

    public TableView<ListaComprasClass> tableListaCompras;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //editar table
        tableListaCompras.setEditable(true);
        colNumero.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colTipoProduto.setCellValueFactory(new PropertyValueFactory<>("tipoProduto"));
        colQtdExistente.setCellValueFactory(new PropertyValueFactory<>("qtdExistente"));
        colQtdComprar.setCellValueFactory(new PropertyValueFactory<>("qtdComprar"));
        for(Linhaencomendafornecedor l: LinhaEncomendaFornecedorCRUD.findAllLinhasEncomendasFornecedores()){
            ListaComprasClass list= new ListaComprasClass();
            list.setId(l.getNumencomenda());
            list.setProduto(l.getProdutoByNumproduto().getNome());
            list.setQtdComprar(l.getQuantidade());
            list.setQtdExistente(l.getProdutoByNumproduto().getQuantidadestock());
            list.setTipoProduto(l.getProdutoByNumproduto().getTipoprodutoByIdtipoproduto().getSeccao());
            tableListaCompras.getItems().add(list);
        }
    }


    public void procurarProduto(){
        int count=0;
        tableListaCompras.setItems(FXCollections.observableArrayList());
        if (procuraproduto.getText().equals("")){
            initialize(null, null);
            return;
        }
        for (Linhaencomendafornecedor linha : LinhaEncomendaFornecedorCRUD.findAllLinhasEncomendasFornecedores()){
            if (this.procuraproduto.getText().equals(Integer.toString(linha.getNumproduto())) ||
            this.procuraproduto.getText().equals(linha.getProdutoByNumproduto().getNome().toLowerCase(Locale.ROOT))){
                count++;
                ListaComprasClass listaComprasClass = new ListaComprasClass();
                listaComprasClass.setId(linha.getNumproduto());
                listaComprasClass.setProduto(linha.getProdutoByNumproduto().getNome());
                listaComprasClass.setTipoProduto(linha.getProdutoByNumproduto().getTipoprodutoByIdtipoproduto().getSeccao());
                listaComprasClass.setQtdExistente(linha.getProdutoByNumproduto().getQuantidadestock());
                listaComprasClass.setQtdComprar(linha.getQuantidade());
                tableListaCompras.getItems().add(listaComprasClass);
            }
        }
        if (count==0){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!");
            dialogoAviso.setHeaderText("Erro! Não existem produtos na lista de compras com os critérios selecionados");
            dialogoAviso.showAndWait();
        }
    }


    public void limparButton(){
        numeroProduto.setText("");
    }

    public void removerCompra(javafx.event.ActionEvent event){
        try {
            ClienteCRUD.deleteCliente(tableListaCompras.getSelectionModel().getSelectedItem().getId());
        } catch (IdNaoEncontradoException e) {
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Houve um erro no sistema! Por favor volte a tentar em algum tempo ou contacte" +
                    " o seu administrador de sistema");
            dialogoAviso.showAndWait();
        }
    }

    public void clicaadicionarProduto(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "AdicionarProdutoListaCompras.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
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
}
