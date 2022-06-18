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

   //ArrayList<Fatura> faturas = new ArrayList<>();
    @FXML
    private Button botaoAtualizaStocks;

    @FXML
    private Button botaoDefinicoesClientes;

    @FXML
    private Button botaoDefinicoesColaborador;

    @FXML
    private Button botaoDefinicoesProdutos;

    @FXML
    private Button botaoListaCompras;

    @FXML
    private Button botaoListaEncomendas;

    @FXML
    private Button btn_procura;

    @FXML
    private TableColumn<Fornecedor, String> fornecedorField;

    @FXML
    private TableColumn<Fornecedor, String> estadoField;

    @FXML
    private TableColumn<Encomendafornecedor, String> moradaField;

    @FXML
    private TableColumn<Fornecedor, String> telefoneField;

    @FXML
    private TableColumn<Fornecedor, Float> valorTotalField;


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


/*
    //LISTAR ENCOMENDAS
    @FXML
    private TableColumn<Fatura, Integer> NumEncomenda;
    @FXML
    private TableColumn<Fatura, String> ClienteField;
    @FXML
    private TableColumn<Moradaentrega, String> moradaField;
    @FXML
    private TableColumn<Cliente, String> telefoneField;
    @FXML
    private TableColumn<Fatura, BigDecimal> valorTotalField;

 */

    /*
            GERENTE MENU PRINCIPAL
     */

    @FXML
    private Label labelPrintVendas;


    @FXML
    private TableView<Fornecedor> tableClientes;
    @FXML
    private TableView<Fornecedor> tableListarEncomendas;



    //DEFINICOES DE COLABORADOR
    @FXML
    private TableColumn<Colaborador, Integer> colNumero;
    @FXML
    private TableColumn<Colaborador, String> colNome;
    @FXML
    private TableColumn<Colaborador, String> colTelefone;
    @FXML
    private TableColumn<Colaborador, BigDecimal> colSalario;
    @FXML
    private TableColumn<Colaborador, Integer> colEstado;

    public TableView<Colaborador> tableColaborador;



     public List<Cliente> listarCliMaisGasto(){
      Map<Cliente,Float> cliMap=guardaValorFaturasCli();
      List<Map.Entry<Cliente,Float>> ordenada=ordenaLista(cliMap);
      return atualiza5MelhoresClientes(ordenada);
     }

     public Map<Cliente,Float> guardaValorFaturasCli(){
      Map<Cliente,Float> melhoresCli=new HashMap<>();
      float valorGastoCli=0;
      for(Cliente cli: ClienteCRUD.findClientesTodos()){
       for(Fatura f: cli.getFaturasByIdcliente()){
        valorGastoCli+=f.getValorfatura().floatValue();
       }
       melhoresCli.put(cli,valorGastoCli);
       valorGastoCli=0;
      }
      return melhoresCli;
     }

     public List<Map.Entry<Cliente,Float>> ordenaLista(Map<Cliente,Float> cliValGasto){
      List<Map.Entry<Cliente,Float>> ordenado=new ArrayList<>(cliValGasto.entrySet());
      ordenado.sort(Map.Entry.comparingByValue());
      return ordenado;
     }

     public List<Cliente> atualiza5MelhoresClientes(List<Map.Entry<Cliente,Float>> ordenado){
      int i=0;
      List<Cliente> melhoresCli=new ArrayList<>();
      Collections.reverse(ordenado);
      for(i=0;i<4;i++){
       for(Map.Entry<Cliente,Float> cli:ordenado) {
        melhoresCli.add(cli.getKey());
       }
      }
      return melhoresCli;
     }

     public float gastos(){
      float acumuladoGastos=0;
      for(Linhaencomendafornecedor lf: LinhaEncomendaFornecedorCRUD.findAllLinhasEncomendasFornecedores()){
       acumuladoGastos+=lf.getValor().floatValue();
      }
      return acumuladoGastos;
     }

     public float vendas(){
      float vendas=0;
      for(Fatura f:FaturaCRUD.findTodasFaturas()){
       vendas+=f.getValorfatura().floatValue();
      }
      return vendas;
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
    public void clicaDefinicoesProdutos(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesProdutos.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaDefinicoesClientes(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "GerentedefinicoesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void ClicaListarEncomendas(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Listagem de encomendas",GerenteController.class);
    }

    public void listaEncomendas(javafx.event.ActionEvent event){
        //dfgdfg
    }

    public Float getValorFatura(){
         float total = 0;
        for(Fatura f: FaturaCRUD.findTodasFaturas()){
            total+=f.getValorfatura().floatValue();
        }
        return total;
    }


 @Override
 public void initialize(URL url, ResourceBundle resourceBundle) {
  List<Cliente> clientes = listarCliMaisGasto();
  txt_cli1.setText(clientes.get(0).getNome());
  txt_cli2.setText(clientes.get(1).getNome());
  txt_cli3.setText(clientes.get(2).getNome());
  txt_cli4.setText(clientes.get(3).getNome());
  txt_cli5.setText(clientes.get(4).getNome());
  txt_acumulaGastos.setText(Float.toString(gastos()));
  txt_acumuladoAnual.setText(Float.toString(vendas()));
  txt_lucro.setText(Float.toString(vendas()-gastos()));
 }
}
