package org.example.fx;

import com.example.bd.CRUD.*;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FormatStringConverter;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.SingleInstance.EncUserTemp;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
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
    private ComboBox<String> comboalteraestado;
    @FXML
    private Button btn_procura;
    @FXML
    private Button btn_consdetalhe;

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
        comboalteraestado.getItems().add("por pagar");
        comboalteraestado.getItems().add("paga");
        comboalteraestado.getItems().add("entregue");
        System.out.println();
        for (Fatura fat: FaturaCRUD.findTodasFaturas()){
            ListaEncomendas lista = new ListaEncomendas();
            lista.setNumFatura(fat.getNumfatura());
            lista.setNomeCliente(fat.getClienteByIdcliente().getNome());
            lista.setMorada(fat.getMoradaentregaByIdentrega().getCodpostal() + "  " + fat.getMoradaentregaByIdentrega().getRua() + "  " + fat.getMoradaentregaByIdentrega().getNumporta());
            lista.setTelefoneCliente(fat.getClienteByIdcliente().getTelefone());
            lista.setValorTotal(fat.getValorfatura().floatValue());
            lista.setIdCliente(fat.getIdcliente());
            try{
                Estado e=EstadoFaturaCRUD.getUltimoEstadoFatura(fat.getNumfatura());
                lista.setEstadoFatura(e.getDescricao());
            }catch(NoResultException ex){
                lista.setEstadoFatura("Erro não existe estado para esta fatura");
            }
            tablelistaencomenda.getItems().add(lista);
        }

    }

    public void alterarEstado(javafx.event.ActionEvent event) {
        Calendar calendar = Calendar.getInstance();
        ListaEncomendas le=tablelistaencomenda.getSelectionModel().getSelectedItem();
        Estado e;
        try{
            e=EstadoCRUD.findEstado(comboalteraestado.getSelectionModel().getSelectedItem());
        }catch (NoResultException except){
            e=new Estado();
            e.setDescricao(comboalteraestado.getSelectionModel().getSelectedItem());
            EstadoCRUD.createEstado(e);
        }
        Estadofatura ef=new Estadofatura();
        ef.setNumfatura(le.getNumFatura());
        ef.setDatafatura(new java.sql.Date((calendar.getTime()).getTime()));
        ef.setIdestado(e.getIdestado());
        try{
            EstadoFaturaCRUD.createEstadoFatura(ef);
        }
        catch(PersistenceException ex){
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
    public void getDetalhesEncomenda(javafx.event.ActionEvent event) throws IOException {
        if(tablelistaencomenda.getSelectionModel().getSelectedItem() != null){
            EncUserTemp.getInstance().setCurrentId(tablelistaencomenda.getSelectionModel().getSelectedItem().getNumFatura());
            TrocaPaineis.changePanel(event, "ColaboradorDetalhesEncomenda.fxml", "Loja Produtos Biológicos", ColaboradorController.class);

        }
    }

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "ColaboradorMenuPrincipal.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }

    public void clicaListaCompras(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaCompras.fxml", "Loja Produtos Biológicos", ColaboradorListaComprasController.class);
    }

    public void clicaListaEncomendas(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaEncomendas.fxml", "Loja Produtos Biológicos", ColaboradorListaEncomendasController.class);
    }

    public void clicaAtualizaStocks(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorAtualizaStocks.fxml", "Loja Produtos Biológicos", ColaboradorAtualizaStocksController.class);
    }
    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ConfirmacaoSaida.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }
    public void clicaadicionarProduto(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "AdicionarProdutoListaCompras.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }


}
