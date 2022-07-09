package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.FornecedorCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Colaborador;
import com.example.bd.Entity.Fornecedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerenteFornecedor implements Initializable {
    @FXML
    private TableColumn<Fornecedor, Integer> colNumero;
    @FXML
    private TableColumn<Fornecedor, String> colFornecedor;
    @FXML
    private TableColumn<Fornecedor, Integer> colMorada;
    @FXML
    private TableColumn<Fornecedor, String> colEmail;
    @FXML
    private TableColumn<Fornecedor, String> colTelefone;
    @FXML
    private TableView<Fornecedor> tableFornecedores;
    @FXML
    private TextField procuraforn;



    public ObservableList<Fornecedor> getFornecedores(){
        ObservableList<Fornecedor> fornecedors = FXCollections.observableArrayList();
        List<Fornecedor> fornecedorList = FornecedorCRUD.findFornecedores();
        fornecedors.addAll(fornecedorList);

        return fornecedors;
    }

    public void editCamposFornecedor(){
        colFornecedor.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        colFornecedor.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Fornecedor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Fornecedor, String> fornecedorStringCellEditEvent) {
                Fornecedor f = fornecedorStringCellEditEvent.getRowValue();
                f.setNome(fornecedorStringCellEditEvent.getNewValue());
                try {
                    FornecedorCRUD.editFornecedor(f);
                } catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText(ex.getMessage());
                    dialogoAviso.showAndWait();
                }
            }
        });
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        colEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Fornecedor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Fornecedor, String> fornecedorStringCellEditEvent) {
                Fornecedor f = fornecedorStringCellEditEvent.getRowValue();
                f.setEmail(fornecedorStringCellEditEvent.getNewValue());
                try {
                    FornecedorCRUD.editFornecedor(f);
                } catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText(ex.getMessage());
                    dialogoAviso.showAndWait();
                }
            }
        });

        colTelefone.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        colTelefone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Fornecedor, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Fornecedor, String> fornecedorStringCellEditEvent) {
                Fornecedor f = fornecedorStringCellEditEvent.getRowValue();
                f.setNumtelefone(fornecedorStringCellEditEvent.getNewValue());
                try {
                    FornecedorCRUD.editFornecedor(f);
                } catch (IdNaoEncontradoException ex){
                    Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                    dialogoAviso.setTitle("ERRO!!");
                    dialogoAviso.setHeaderText(ex.getMessage());
                    dialogoAviso.showAndWait();
                }
            }
        });
    }

    public void pesquisaFornecedor(javafx.event.ActionEvent event){
        if(procuraforn.getText() != null){
            tableFornecedores.setItems(FXCollections.observableArrayList());
            List<Fornecedor> result=FornecedorCRUD.findByDados(procuraforn.getText());
            if(result.size() == 0){
                Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
                dialogoAviso.setTitle("ERRO!!");
                dialogoAviso.setHeaderText("Não existem Fornecedores para o critérios de pesquisa referenciados!!");
                dialogoAviso.showAndWait();
                initialize(null,null);
            }
            else{
                tableFornecedores.getItems().addAll(result);
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableFornecedores.setEditable(true);
        colNumero.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
        colFornecedor.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colMorada.setCellValueFactory(new PropertyValueFactory<>("rua"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("numtelefone"));
        editCamposFornecedor();
        tableFornecedores.setItems(getFornecedores());
    }


    public void adicionarFornecedor(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "AdicionarFornecedor.fxml", "Loja Produtos Biológicos", GerenteController.class);
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
    public void clicaDefinicoesClientes(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void ClicaListarEncomendas(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Listagem de encomendas",GerenteController.class);
    }

    public void clicaDefinicoesFornecedor(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteFornecedores.fxml","Listagem de encomendas",GerenteController.class);
    }


}
