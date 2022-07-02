package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Fatura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VerDetalhesCliente implements Initializable {


    @FXML
    public TableView<Cliente> tableCliente;
    @FXML
    private TableColumn<Cliente, Integer> numCliente;
    @FXML
    private TableColumn<Cliente, String> nomeCliente;
    @FXML
    private TableColumn<Cliente, String> telefoneCliente;
    @FXML
    private TableColumn<Cliente, String> codPostalCliente;
    @FXML
    private TableColumn<Cliente, String> ruaCliente;

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
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Listagem de encomendas",GerenteController.class);
    }

    public void buttonBack(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteMenuPrincipal.fxml","Listagem de encomendas",GerenteController.class);
    }


    public ObservableList<Cliente> getClientes(){
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        List<Cliente> clienteList = ClienteCRUD.findClientesTodos();
        clientes.addAll(clienteList);
        return clientes;
    }
    public void listarCliente(){
        for (Cliente c : ClienteCRUD.findClientesTodos()){
            numCliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
            nomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
            telefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            codPostalCliente.setCellValueFactory(new PropertyValueFactory<>("codpostal"));
            ruaCliente.setCellValueFactory(new PropertyValueFactory<>("rua"));
            tableCliente.setItems(getClientes());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listarCliente();

    }
}
