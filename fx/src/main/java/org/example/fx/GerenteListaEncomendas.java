package org.example.fx;

import com.example.bd.CRUD.EstadoFaturaCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.fx.Logica.TrocaPaineis;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
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
    private Button btn_consulta;

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
            try {
                Estado e=EstadoFaturaCRUD.getUltimoEstadoFatura(fat.getNumfatura());
                lista.setEstadoFatura(e.getDescricao());
            } catch (NoResultException ex){
            }
            tablelistaencomenda.getItems().add(lista);
        }
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
    public void verDetalhes() throws Exception{
        Stage stage = new Stage();
        FXMLLoader fx = new FXMLLoader(ColaboradorListaEncomendasController.class.getResource("VerDetalhesListaCompras.fxml"));
        Scene scene = new Scene(fx.load(), 539, 430);
        stage.setScene(scene);
        stage.show();
    }

    public void viewDetails(){
        ListaEncomendas le = tablelistaencomenda.getSelectionModel().getSelectedItem();
        if (le == null){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Selecione uma encomenda");
            dialogoAviso.showAndWait();
        }
        else {
            try {
                verDetalhes();
            } catch (Exception ex){}
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
