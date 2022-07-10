package org.example.fx;

import com.example.bd.CRUD.*;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class GerenteController implements Initializable {
    @FXML
    private Text txt_acumuladoAnual;
    @FXML
    private Text txt_cli1;
    @FXML
    private Text txt_cli2;
    @FXML
    private Text txt_cli3;
    @FXML
    private Text txt_cli4;
    @FXML
    private Text txt_cli5;
    @FXML
    private Text txt_acumulaGastos;
    @FXML
    private Text txt_lucro;
    @FXML
    private Button button_cliente1;
    @FXML
    private Button button_cliente2;
    @FXML
    private Button button_cliente3;
    @FXML
    private Button button_cliente4;
    @FXML
    private Button button_cliente5;

    public float gastos(){
      return EncomendaFornecedorCRUD.findGastos().floatValue();
     }

     public float vendas(){
      return FaturaCRUD.findLucros().floatValue();
     }



    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
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
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Loja Produtos Biológicos",GerenteController.class);
    }
    public void clicaDefinicoesFornecedor(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteFornecedores.fxml","Loja Produtos Biológicos",GerenteController.class);
    }

    public void clicaTipoProduto(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"TiposProduto.fxml","Loja Produtos Biológicos",GerenteController.class);
    }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
    List<Cliente> clientes = ClienteCRUD.findMelhores();
    try{
        if(clientes.get(0) != null){
            txt_cli1.setText(clientes.get(0).getNome()+" // "+clientes.get(0).getTelefone());
        }
    }
    catch(IndexOutOfBoundsException ex){
        txt_cli1.setText(null);
    }

    try{
     if(clientes.get(1) != null){
      txt_cli2.setText(clientes.get(1).getNome()+ " // "+clientes.get(1).getTelefone());
     }
    }
    catch (IndexOutOfBoundsException ex){
     txt_cli2.setText(null);
    }
    try{
     if(clientes.get(2) != null){
      txt_cli3.setText(clientes.get(2).getNome()+" // "+clientes.get(2).getTelefone());
     }
    }
    catch (IndexOutOfBoundsException ex) {
     txt_cli3.setText(null);
    }
    try{
     if(clientes.get(3) != null){
      txt_cli4.setText(clientes.get(3).getNome()+" // "+clientes.get(3).getTelefone());
     }
    }
    catch (IndexOutOfBoundsException ex) {
     txt_cli4.setText(null);
    }
    try{
     if(clientes.get(4) != null){
      txt_cli5.setText(clientes.get(4).getNome()+" // "+clientes.get(0).getTelefone());
     }
    }
    catch (IndexOutOfBoundsException ex) {
     txt_cli5.setText(null);
    }
       try{
           txt_acumulaGastos.setText(Float.toString(gastos()));
           txt_acumuladoAnual.setText(Float.toString(vendas()));
           txt_lucro.setText(Float.toString(vendas()-gastos()));
       }
       catch(NullPointerException ex){
           txt_acumuladoAnual.setText(null);
           txt_acumulaGastos.setText(null);
           txt_lucro.setText(null);
       }
   }
}
