package org.example.fx;

import com.example.bd.CRUD.EstadoFaturaCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;

import javax.persistence.RollbackException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class GerenteListaEncomendas implements Initializable{

    @FXML
    private TableColumn<ListaEncomendas, Integer> numfatura;

    @FXML
    private TableColumn<ListaEncomendas, String> nomecliente;

    @FXML
    private TableColumn<ListaEncomendas, String> moradacliente;

    @FXML
    private TableColumn<ListaEncomendas, String> telefonecliente;

    @FXML
    private TableColumn<ListaEncomendas, Float> valortotalfatura;

    @FXML
    private TableColumn<ListaEncomendas, String> estadofatura;

    @FXML
    private TableView<ListaEncomendas> tablelistaencomenda;

    @FXML
    private Button btn_procura;

    @FXML
    private TextField lbl_cliente;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int idEstado=0;
        numfatura.setCellValueFactory(new PropertyValueFactory<>("numFatura"));
        nomecliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        moradacliente.setCellValueFactory(new PropertyValueFactory<>("morada"));
        telefonecliente.setCellValueFactory(new PropertyValueFactory<>("telefoneCliente"));
        valortotalfatura.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        estadofatura.setCellValueFactory(new PropertyValueFactory<>("estadoFatura"));
        for (Fatura fat: FaturaCRUD.findTodasFaturas()){
            ListaEncomendas lista = new ListaEncomendas();
            lista.setNumFatura(fat.getNumfatura());
            lista.setNomeCliente(fat.getClienteByIdcliente().getNome());
            lista.setMorada(fat.getMoradaentregaByIdentrega().getCodpostal() + "  " + fat.getMoradaentregaByIdentrega().getRua() + "  " + fat.getMoradaentregaByIdentrega().getNumporta());
            lista.setTelefoneCliente(fat.getClienteByIdcliente().getTelefone());
            lista.setValorTotal(fat.getValorfatura().floatValue());
            for(Estadofatura ef:EstadoFaturaCRUD.findAllEstadosFaturas()){
                if(ef.getNumfatura()== fat.getNumfatura()){
                    lista.setEstadoFatura(ef.getEstadoByIdestado().getDescricao());
                }
            }
            tablelistaencomenda.getItems().add(lista);
        }
    }

    public void pesquisa(javafx.event.ActionEvent event){
        tablelistaencomenda.setItems(FXCollections.observableArrayList());
        int cont=0;
        if (lbl_cliente.getText().equals("")){
            initialize(null, null);
            return;
        }
        for (Fatura fat: FaturaCRUD.findTodasFaturas()){
            if(lbl_cliente.getText().equals(Integer.toString(fat.getIdcliente())) ||
                    lbl_cliente.getText().equals(fat.getClienteByIdcliente().getNome()) ||
                            lbl_cliente.getText().equals(fat.getClienteByIdcliente().getTelefone())){
                cont++;
                ListaEncomendas lista = new ListaEncomendas();
                lista.setNumFatura(fat.getNumfatura());
                lista.setNomeCliente(fat.getClienteByIdcliente().getNome());
                lista.setMorada(fat.getMoradaentregaByIdentrega().getCodpostal() + "  " + fat.getMoradaentregaByIdentrega().getRua() + "  " + fat.getMoradaentregaByIdentrega().getNumporta());
                lista.setTelefoneCliente(fat.getClienteByIdcliente().getTelefone());
                lista.setValorTotal(fat.getValorfatura().floatValue());
                for(Estadofatura ef:EstadoFaturaCRUD.findAllEstadosFaturas()){
                    if(ef.getNumfatura()== fat.getNumfatura()){
                        lista.setEstadoFatura(ef.getEstadoByIdestado().getDescricao());
                    }
                }
                tablelistaencomenda.getItems().add(lista);
            }
        }
        if(cont==0){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Não existe nenhuma encomenda para o cliente referido!!");
            dialogoAviso.showAndWait();
        }
    }
    public void alterarEstado(javafx.event.ActionEvent event) {
        java.util.Date data= new java.util.Date();
        java.sql.Date date= new java.sql.Date(data.getYear()+1900,data.getMonth()+1,data.getDay());
        ListaEncomendas le=tablelistaencomenda.getSelectionModel().getSelectedItem();
        Estadofatura ef=new Estadofatura();
        ef.setIdestado(2);
        ef.setNumfatura(le.getNumFatura());
        ef.setDatafatura(date);
        try{
            EstadoFaturaCRUD.createEstadoFatura(ef);
        }
        catch(RollbackException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Não pode alterar o estado desta fatura!!");
            dialogoAviso.showAndWait();
        }
        tablelistaencomenda.setItems(FXCollections.observableArrayList());
        initialize(null,null);
    }


    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "GerenteMenuPrincipal.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ConfirmacaoSaida.fxml", "Loja Produtos Biológicos", GerenteController.class);
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
}
