package org.example.fx;

import com.example.bd.CRUD.EncomendaFornecedorCRUD;
import com.example.bd.CRUD.LinhaEncomendaFornecedorCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Encomendafornecedor;
import com.example.bd.Entity.Linhaencomendafornecedor;
import com.example.bd.Entity.LinhaencomendafornecedorPK;
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
import org.example.fx.SingleInstance.EncUserTemp;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ColaboradorListaComprasController implements Initializable {
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

    public void procurarProduto(javafx.event.ActionEvent event){
        int cont=0;
        if(procuraproduto.getText() != null){
            tableListaCompras.setItems(FXCollections.observableArrayList());
            List<Encomendafornecedor> result=EncomendaFornecedorCRUD.getByFornecedor(procuraproduto.getText());
            for(Encomendafornecedor ef:result){
                cont++;
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
            if(cont==0){
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("ERRO!!");
                dialogoAviso.setHeaderText("Erro! Não existe nenhum fornededor com estes dados!");
                dialogoAviso.showAndWait();
                initialize(null,null);
            }
        }else{
            initialize(null,null);
        }
    }
    public void buttonBack(javafx.event.ActionEvent event) throws IOException {
        clicaListaCompras(event);
    }
    public void getDetalhesCompra(javafx.event.ActionEvent event) throws IOException {
        if(tableListaCompras.getSelectionModel().getSelectedItem() != null){
            EncUserTemp.getInstance().setCurrentId(tableListaCompras.getSelectionModel().getSelectedItem().getId());
            TrocaPaineis.changePanel(event, "ColaboradorDetalhesCompra.fxml", "Loja Produtos Biológicos",
                    ColaboradorController.class);
        }
    }

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "ColaboradorMenuPrincipal.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
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
    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ConfirmacaoSaida.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }
    public void clicaadicionarProduto(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "AdicionarProdutoListaCompras.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }
}
