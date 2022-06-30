package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.EstadoFaturaCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;

import javax.persistence.RollbackException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ColaboradorListaEncomendasController implements Initializable {

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
            lista.setIdCliente(fat.getIdcliente());
            Estado e=EstadoFaturaCRUD.getUltimoEstadoFatura(fat.getNumfatura());
            lista.setEstadoFatura(e.getDescricao());
            tablelistaencomenda.getItems().add(lista);
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

    public void pesquisa(javafx.event.ActionEvent event){
        tablelistaencomenda.setItems(FXCollections.observableArrayList());
        boolean isNumber=false;
        int cont=0;
        int idcli=0;
        if (lbl_cliente.getText().equals("")){
            initialize(null, null);
            return;
        }
        try{
            idcli=Integer.parseInt(lbl_cliente.getText());
            isNumber=true;
        }
        catch (NumberFormatException ex){
            isNumber=false;
        }
        if(!isNumber){
            List<Fatura> result=FaturaCRUD.findByTelefoneNomeCli(lbl_cliente.getText(),lbl_cliente.getText());
            for(Fatura fat:result){
                ListaEncomendas lista = new ListaEncomendas();
                lista.setNumFatura(fat.getNumfatura());
                lista.setNomeCliente(fat.getClienteByIdcliente().getNome());
                lista.setMorada(fat.getMoradaentregaByIdentrega().getCodpostal() + "  " + fat.getMoradaentregaByIdentrega().getRua() + "  " + fat.getMoradaentregaByIdentrega().getNumporta());
                lista.setTelefoneCliente(fat.getClienteByIdcliente().getTelefone());
                lista.setValorTotal(fat.getValorfatura().floatValue());
                Estado e=EstadoFaturaCRUD.getUltimoEstadoFatura(fat.getNumfatura());
                lista.setEstadoFatura(e.getDescricao());
            }

        }

        if(cont==0){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Não existe nenhuma encomenda para o cliente referido!!");
            dialogoAviso.showAndWait();
        }
    }

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "ColaboradorMenuPrincipal.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaListaCompras(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaCompras.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void ClicaListarEncomendas(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"ColaboradorListaEncomendas.fxml","Listagem de encomendas",GerenteController.class);
    }

    public void clicaAtualizaStockProdutos(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorAtualizaStocks.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }
    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ConfirmacaoSaida.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }
}
