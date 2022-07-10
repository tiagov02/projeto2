package org.example.fx;

import com.example.bd.CRUD.TipoProdutoCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Tipoproduto;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GerenteTiposProdutosController implements Initializable {
    @FXML
    private TableColumn<Tipoproduto, Integer> colId;

    @FXML
    private TableColumn<Tipoproduto, String> coldescricao;

    @FXML
    private TableView<Tipoproduto> tableTipos;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("idtipoproduto"));
        coldescricao.setCellValueFactory(new PropertyValueFactory<>("seccao"));
        tableTipos.getItems().addAll(TipoProdutoCRUD.findTiposProduto());
        coldescricao.setCellFactory(TextFieldTableCell.forTableColumn());

    }
    public void editTipos(){
        coldescricao.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tipoproduto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Tipoproduto, String> tipoprodutoStringCellEditEvent) {
                Tipoproduto tp=tipoprodutoStringCellEditEvent.getRowValue();
                tp.setSeccao(tipoprodutoStringCellEditEvent.getNewValue());
                try{
                    TipoProdutoCRUD.editFornecedor(tp);
                }catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText(ex.getMessage());
                    dialogoAviso.showAndWait();
                }
            }
        });
    }

    public void clicaLogout(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "ConfirmacaoSaida.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }


    public void clicaCliente1(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "VerDetalhesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaCliente2(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "VerDetalhesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaCliente3(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "VerDetalhesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaCliente4(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "VerDetalhesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaCliente5(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "VerDetalhesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException{
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

    public void clicaDefinicoesClientes(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void ClicaListarEncomendas(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Loja Produtos Biológicos",GerenteController.class);
    }
    public void clicaDefinicoesFornecedor(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteFornecedores.fxml","Loja Produtos Biológicos",GerenteController.class);
    }

    public void clicaTipoProduto(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"TiposProduto.fxml","Loja Produtos Biológicos",GerenteController.class);
    }
}
