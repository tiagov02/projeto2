package org.example.fx;

import com.example.bd.CRUD.EstadoCRUD;
import com.example.bd.CRUD.EstadoFaturaCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.SingleInstance.EncUserTemp;

import javax.persistence.NoResultException;
import javax.persistence.RollbackException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
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
    private ComboBox<String> comboalteraestado;

    @FXML
    private Button btn_consulta;

    @FXML
    private TextField lbl_cliente;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //test();
        numfatura.setCellValueFactory(new PropertyValueFactory<>("numFatura"));
        nomecliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        moradacliente.setCellValueFactory(new PropertyValueFactory<>("morada"));
        telefonecliente.setCellValueFactory(new PropertyValueFactory<>("telefoneCliente"));
        valortotalfatura.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        estadofatura.setCellValueFactory(new PropertyValueFactory<>("estadoFatura"));
        comboalteraestado.getItems().add("por pagar");
        comboalteraestado.getItems().add("paga");
        comboalteraestado.getItems().add("entregue");
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
                lista.setEstadoFatura("Erro! não existe nenhum estado para esta fatura!");
            }
            tablelistaencomenda.getItems().add(lista);
        }
    }

    public void pesquisa(javafx.event.ActionEvent event){
        tablelistaencomenda.setItems(FXCollections.observableArrayList());
        boolean isNumber=false;
        int idcli=0;
        if (lbl_cliente.getText().equals("")){
            tablelistaencomenda.setItems(FXCollections.observableArrayList());
            comboalteraestado.setItems(FXCollections.observableArrayList());
            initialize(null, null);
            return;
        }
        List<Fatura> result=FaturaCRUD.findByTelefoneNomeCli(lbl_cliente.getText(),lbl_cliente.getText());
        if(result.size() == 0){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Não existe nenhuma encomenda para o cliente referido!!");
            dialogoAviso.showAndWait();
            comboalteraestado.setItems(FXCollections.observableArrayList());
            initialize(null,null);
        }
        for(Fatura fat:result){
            ListaEncomendas lista = new ListaEncomendas();
            lista.setNumFatura(fat.getNumfatura());
            lista.setNomeCliente(fat.getClienteByIdcliente().getNome());
            lista.setMorada(fat.getMoradaentregaByIdentrega().getCodpostal() + "  " + fat.getMoradaentregaByIdentrega().getRua() + "  " + fat.getMoradaentregaByIdentrega().getNumporta());
            lista.setTelefoneCliente(fat.getClienteByIdcliente().getTelefone());
            lista.setValorTotal(fat.getValorfatura().floatValue());
            Estado e=EstadoFaturaCRUD.getUltimoEstadoFatura(fat.getNumfatura());
            lista.setEstadoFatura(e.getDescricao());
            tablelistaencomenda.getItems().add(lista);
        }
    }

    public void test(){
        ActionEvent event = new ActionEvent();
        btn_consulta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent){
                Stage stage = new Stage();
                FXMLLoader fx = new FXMLLoader(HelloApplication.class.getResource("VerDetalhesListaCompras.fxml"));
                try {
                    Scene scene = new Scene(fx.load(), 320, 240);
                    stage.show();
                    stage.setScene(scene);

                    //TrocaPaineis.changePanel(event,"VerDetalhesListaCompras.fxml", "Detalhes Encomenda", GerenteListaEncomendas.class);
                } catch (Exception ex){}

            }
        });
        /*
        Stage stage = new Stage();
        FXMLLoader fx = new FXMLLoader(HelloApplication.class.getResource("VerDetalhesListaCompras.fxml"));
        Scene scene = new Scene(fx.load(), 320, 240);
        stage.setScene(scene);
        stage.show();

         */
        /*
        ListaEncomendas le = tablelistaencomenda.getSelectionModel().getSelectedItem();
        if (le == null){
            Alert dialogoAviso = new Alert(Alert.AlertType.ERROR);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Selecione uma encomenda!!");
            dialogoAviso.showAndWait();
        }
        else{
            FXMLLoader fx = new FXMLLoader(getClass().getResource("VerDetalhesListaCompras.fxml"));
            fx.setController(new PopupController(le.getNumFatura()));
            Stage stage = new Stage();
            Scene scene = new Scene(fx.load(), 539, 430);
            stage.setScene(scene);
            stage.show();
        }

         */

    }
    public void alterarEstado(javafx.event.ActionEvent event) {
        Calendar calendar = Calendar.getInstance();
        ListaEncomendas le=tablelistaencomenda.getSelectionModel().getSelectedItem();
        Estado e;
        try{
            e= EstadoCRUD.findEstado(comboalteraestado.getSelectionModel().getSelectedItem());
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
        catch(RollbackException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Erro! Não pode alterar o estado desta fatura!!");
            dialogoAviso.showAndWait();
        }
        tablelistaencomenda.setItems(FXCollections.observableArrayList());
        comboalteraestado.setItems(FXCollections.observableArrayList());
        initialize(null,null);
    }

    public void getDetalhesEncomenda(javafx.event.ActionEvent event) throws IOException {
        if(tablelistaencomenda.getSelectionModel().getSelectedItem() != null){
            EncUserTemp.getInstance().setCurrentId(tablelistaencomenda.getSelectionModel().getSelectedItem().getNumFatura());
            TrocaPaineis.changePanel(event, "DetalhesEncomenda.fxml", "Loja Produtos Biológicos", GerenteController.class);
        }
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
    public void clicaDefinicoesFornecedor(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteFornecedores.fxml","Listagem de encomendas",GerenteController.class);
    }
}
