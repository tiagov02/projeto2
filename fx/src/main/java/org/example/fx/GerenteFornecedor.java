package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.FornecedorCRUD;
import com.example.bd.Entity.Colaborador;
import com.example.bd.Entity.Fornecedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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



    public ObservableList<Fornecedor> getFornecedores(){
        ObservableList<Fornecedor> fornecedors = FXCollections.observableArrayList();
        List<Fornecedor> fornecedorList = FornecedorCRUD.findFornecedores();
        fornecedors.addAll(fornecedorList);

        return fornecedors;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tableFornecedores.setEditable(true);
        for (Fornecedor f : FornecedorCRUD.findFornecedores()){
            colNumero.setCellValueFactory(new PropertyValueFactory<>("idfornecedor"));
            colFornecedor.setCellValueFactory(new PropertyValueFactory<>("nome"));
            colMorada.setCellValueFactory(new PropertyValueFactory<>("rua"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colTelefone.setCellValueFactory(new PropertyValueFactory<>("numtelefone"));
            tableFornecedores.setItems(getFornecedores());
        }
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
