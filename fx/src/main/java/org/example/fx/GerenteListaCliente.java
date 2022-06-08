package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Produto;
import com.example.bd.Entity.Tipocliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerenteListaCliente implements Initializable {
    @FXML
    private TextField texto;
    @FXML
    private TableColumn<ClienteTipo, Integer> numcliente;
    @FXML
    private TableColumn<ClienteTipo, String> nomecliente;
    @FXML
    private TableColumn<ClienteTipo, String> tipocliente;
    @FXML
    private TableColumn<ClienteTipo, String> telefone;
    @FXML
    private TableColumn<ClienteTipo, String> morada;
    @FXML
    private TableColumn<ClienteTipo, String> codpostal;

    public TableView<ClienteTipo> tableClientes;

    public ObservableList<Cliente> getClientes(){
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        List<Cliente> clienteList = ClienteCRUD.findClientesTodos();
        clientes.addAll(clienteList);

        return clientes;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numcliente.setCellValueFactory(new PropertyValueFactory<>("numCliente"));
        nomecliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tipocliente.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        morada.setCellValueFactory(new PropertyValueFactory<>("morada"));
        ClienteTipo clienteTipo = new ClienteTipo();
        for (Cliente cliente: ClienteCRUD.findClientesTodos()){
            clienteTipo.setNumCliente(cliente.getIdcliente());
            clienteTipo.setNomeCliente(cliente.getNome());
            clienteTipo.setTelefone(cliente.getTelefone());
            clienteTipo.setMorada(cliente.getCodpostaisByCodpostal().getCodpostal() + '-' + cliente.getCodpostaisByCodpostal().getLocalidade());
            tableClientes.getItems().add(clienteTipo);
        }
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
