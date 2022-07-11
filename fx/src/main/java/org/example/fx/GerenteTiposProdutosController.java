package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.TipoProdutoCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Colaborador;
import com.example.bd.Entity.Tipoproduto;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.DefaultStringConverter;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
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
        tableTipos.setEditable(true);
        editTipos();
        colId.setCellValueFactory(new PropertyValueFactory<>("idtipoproduto"));
        coldescricao.setCellValueFactory(new PropertyValueFactory<>("seccao"));
        tableTipos.getItems().addAll(TipoProdutoCRUD.findTiposProduto());
        coldescricao.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void editTipos(){
        coldescricao.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        coldescricao.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Tipoproduto, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Tipoproduto, String> tipoprodutoStringCellEditEvent) {
                Tipoproduto tipo = tipoprodutoStringCellEditEvent.getRowValue();
                tipo.setSeccao(tipoprodutoStringCellEditEvent.getNewValue());
                try {
                    TipoProdutoCRUD.editTipoProduto(tipo);
                } catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText(ex.getMessage());
                    dialogoAviso.showAndWait();
                }
            }
        });
    }
    public void AddTiposProduto(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "AddTiposProduto.fxml", "Loja Produtos Biológicos", GerenteController.class);

    }
    public void clicaLogout(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "ConfirmacaoSaida.fxml", "Loja Produtos Biológicos", GerenteController.class);
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
