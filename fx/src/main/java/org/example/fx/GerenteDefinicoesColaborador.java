package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.TipoColaboradorCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Colaborador;
import com.example.bd.Entity.Tipocolaborador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerenteDefinicoesColaborador implements Initializable {

    @FXML
    private TableColumn<Colaborador, Integer> colNumero;
    @FXML
    private TableColumn<Colaborador, String> colNome;
    @FXML
    private TableColumn<Colaborador, String> colTelefone;
    @FXML
    private TableColumn<Colaborador, BigDecimal> colsalario;
    @FXML
    private TableColumn<Colaborador, String> colEstado;

    public TableView<Colaborador> tableColaborador;


    public void editCamposColaborador(){
        colsalario.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        colsalario.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Colaborador, BigDecimal>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Colaborador, BigDecimal> colaboradorBigDecimalCellEditEvent) {
                Colaborador colaborador = colaboradorBigDecimalCellEditEvent.getRowValue();
                colaborador.setSalario(colaboradorBigDecimalCellEditEvent.getNewValue());
                try {
                    ColaboradorCRUD.editColaborador(colaborador);
                } catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText(ex.getMessage());
                    dialogoAviso.showAndWait();
                }
            }
        });
        colTelefone.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        colTelefone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Colaborador, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Colaborador, String> colaboradorStringCellEditEvent) {
                Colaborador colab = colaboradorStringCellEditEvent.getRowValue();
                colab.setTelefone(colaboradorStringCellEditEvent.getNewValue());
                try {
                    ColaboradorCRUD.editColaborador(colab);
                } catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText(ex.getMessage());
                    dialogoAviso.showAndWait();
                }
            }
        });
        colNome.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        colNome.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Colaborador, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Colaborador, String> colaboradorStringCellEditEvent) {
                Colaborador colab = colaboradorStringCellEditEvent.getRowValue();
                colab.setNome(colaboradorStringCellEditEvent.getNewValue());
                try {
                    ColaboradorCRUD.editColaborador(colab);
                } catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText(ex.getMessage());
                    dialogoAviso.showAndWait();
                }
            }
        });
        colEstado.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        colEstado.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Colaborador, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Colaborador, String> colaboradorStringCellEditEvent) {
                Colaborador colab = colaboradorStringCellEditEvent.getRowValue();
                colab.setEstado(colaboradorStringCellEditEvent.getNewValue());
                try {
                    ColaboradorCRUD.editColaborador(colab);
                } catch (IdNaoEncontradoException ex){
                    ex.getMessage();
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableColaborador.setEditable(true);
        editCamposColaborador();
       for (Colaborador colab: ColaboradorCRUD.findTodosColaboradores()){
               colNumero.setCellValueFactory(new PropertyValueFactory<>("idcolaborador"));
               colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
               colTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
               colsalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
               colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
               tableColaborador.setItems(getColaborador());
       }
    }

    public ObservableList<Colaborador> getColaborador(){
        ObservableList<Colaborador> colaboradors = FXCollections.observableArrayList();
        List<Colaborador> colaboradorList = ColaboradorCRUD.findTodosColaboradores();
        colaboradors.addAll(colaboradorList);

        return colaboradors;
    }

    public void adicionarColaborador(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "AdicionarColaborador.fxml", "Loja Produtos Biológicos", GerenteController.class);
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
