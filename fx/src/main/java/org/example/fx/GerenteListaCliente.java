package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.Entity.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.ModelClasses.ClienteTipo;

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

    @FXML
    private Button btn_cli;

    @FXML
    private TextField lbl_cli;

    public ObservableList<Cliente> getClientes(){
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        List<Cliente> clienteList = ClienteCRUD.findClientesTodos();
        clientes.addAll(clienteList);

        return clientes;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableClientes.setEditable(true);
        numcliente.setCellValueFactory(new PropertyValueFactory<>("numCliente"));
        nomecliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tipocliente.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        morada.setCellValueFactory(new PropertyValueFactory<>("morada"));

        for (Cliente cliente: ClienteCRUD.findClientesTodos()){
            ClienteTipo clienteTipo = new ClienteTipo();
            clienteTipo.setNumCliente(cliente.getIdcliente());
            clienteTipo.setNomeCliente(cliente.getNome());
            clienteTipo.setTipoCliente(cliente.getTipoclienteByIdtipocliente().getTipocliente());
            clienteTipo.setTelefone(cliente.getTelefone());
            clienteTipo.setMorada(cliente.getRua()+","+cliente.getNumporta()+" , "+
                    cliente.getCodpostaisByCodpostal().getCodpostal() + " , "
                    + cliente.getCodpostaisByCodpostal().getLocalidade());
            tableClientes.getItems().add(clienteTipo);
        }
    }

    public void pesquisaCli(javafx.event.ActionEvent event){
        int cont=0;
        tableClientes.setItems(FXCollections.observableArrayList());
        if(lbl_cli.getText().equals("")){
            initialize(null, null);
            return;
        }
        List<Cliente> clis=ClienteCRUD.findCliByData(lbl_cli.getText());
        if(clis.size() == 0){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!");
            dialogoAviso.setHeaderText("Erro! Não existem clientes com os critérios de pesquisa selecionados!");
            dialogoAviso.showAndWait();
            initialize(null,null);
        }
        for(Cliente cli: clis){
            ClienteTipo clienteTipo = new ClienteTipo();
            clienteTipo.setNumCliente(cli.getIdcliente());
            clienteTipo.setNomeCliente(cli.getNome());
            clienteTipo.setTipoCliente(cli.getTipoclienteByIdtipocliente().getTipocliente());
            clienteTipo.setTelefone(cli.getTelefone());
            clienteTipo.setMorada(cli.getRua()+","+cli.getNumporta()+" , "+
                    cli.getCodpostaisByCodpostal().getCodpostal() + " , "
                    + cli.getCodpostaisByCodpostal().getLocalidade());
            tableClientes.getItems().add(clienteTipo);
        }
    }


    public void clicaInsertCliente(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "AdicionarCliente.fxml", "Loja Produtos Biológicos", AdicionarClienteController.class);
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
    public void clicaTipoProduto(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"TiposProduto.fxml","Loja Produtos Biológicos",GerenteController.class);
    }
}
