package org.example.fx;

import com.example.bd.CRUD.*;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.ModelClasses.ListaComprasClass;
import org.example.fx.ModelClasses.ModelEncomendaFornecedor;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GerenteListaCompras implements Initializable {


    @FXML
    private TableColumn<ListaComprasClass, Integer> colNumero;

    @FXML
    private TextField procuraproduto;

    public TableView<ModelEncomendaFornecedor> tableListaCompras;

    @FXML
    private TableColumn<ModelEncomendaFornecedor, String> colFornecedor;

    @FXML
    private TableColumn<ModelEncomendaFornecedor, Float> colValTotal;

    @FXML
    private TableColumn<ModelEncomendaFornecedor, String> colMorada;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //editar table
        //tableListaCompras.setEditable(true);
        //colNumero.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFornecedor.setCellValueFactory(new PropertyValueFactory<>("nomeFornecedor"));
        colValTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
        colMorada.setCellValueFactory(new PropertyValueFactory<>("morada"));

        for(Encomendafornecedor ef:EncomendaFornecedorCRUD.findTodasEncomendasFornecedores()){
            ModelEncomendaFornecedor enc=new ModelEncomendaFornecedor();
            enc.setId(ef.getNumencomenda());
            enc.setNomeFornecedor(ef.getFornecedorByIdfornecedor().getNome());
            enc.setPrecoTotal(ef.getValortotal().floatValue());
            String morada=ef.getFornecedorByIdfornecedor().getRua()+" , "+ef.getFornecedorByIdfornecedor().getNumporta()+
                    " , "+ef.getFornecedorByIdfornecedor().getCodpostal()+" , "
                    +ef.getFornecedorByIdfornecedor().getCodpostaisByCodpostal().getLocalidade();
            enc.setMorada(morada);
            tableListaCompras.getItems().add(enc);
        }
    }


    /*public void procurarProduto(){
        boolean isNumber=false;
        int count=0;
        tableListaCompras.setItems(FXCollections.observableArrayList());
        if (procuraproduto.getText().equals("")){
            initialize(null, null);
            return;
        }
        String procurar=this.procuraproduto.getText().toLowerCase(Locale.ROOT);
        int num=0;
        try{
            num=Integer.parseInt(this.procuraproduto.getText());
            isNumber=true;
        }
        catch(NumberFormatException ex){
            isNumber=false;
        }
        if(isNumber){
            List<Linhaencomendafornecedor> linhasproc=LinhaEncomendaFornecedorCRUD.pesquisaIdProd(num);
            for(Linhaencomendafornecedor linha:linhasproc){
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
        else{
            List<Linhaencomendafornecedor> linhasproc=LinhaEncomendaFornecedorCRUD.pesquisa(procurar);
            for(Linhaencomendafornecedor linha:linhasproc){
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
    }*/


    public void limparButton(){
        procuraproduto.setText("");
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
