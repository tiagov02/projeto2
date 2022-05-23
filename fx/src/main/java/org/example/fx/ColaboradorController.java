package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Fatura;
import com.example.bd.Entity.Produto;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.KeyStore;
import java.util.*;

public class ColaboradorController{
    @FXML
    private Button botaoPaginaPrincipal;

    @FXML
    private Button botaoListaCompras;

    @FXML
    private Button botaoListarEncomendas;

    @FXML
    private Button botaoAtualizaStocks;

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
    private TableColumn<Produto, Integer> tbl_QtdStock;

    @FXML
    private TableColumn<Produto, Integer> tbl_qtdMinima;

    @FXML
    private TableColumn<Produto, BigDecimal> tbl_valUnitario;

    @FXML
    private TableView<Produto> table_produtos;

    @FXML
    private TableColumn<Produto, String> tbl_nomeProd;

    @FXML
    private TableColumn<Produto, Integer> tbl_tipoProduto;

    /**
     * Mudar para string
     */

    public void atualiza(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"ColaboradorAtualizaStrocks","Atualização de stocks",ColaboradorController.class);
        tbl_nomeProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbl_tipoProduto.setCellValueFactory(new PropertyValueFactory<>("idtipoproduto"));
        tbl_QtdStock.setCellValueFactory(new PropertyValueFactory<>("quantidadestock"));
        tbl_qtdMinima.setCellValueFactory(new PropertyValueFactory<>("quantidademinima"));
        tbl_valUnitario.setCellValueFactory(new PropertyValueFactory<>("valorunitariototal"));

        for(Produto p: ProdutoCRUD.findTodosProdutos()){
            table_produtos.getItems().add(p);
        }
    }

    public Map<Cliente,Float> guardaValorFaturasCli(){
        Map<Cliente,Float> melhoresCli=new HashMap<>();
        float valorGastoCli=0;
        //String[] melhoresCli=new String[5];
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

    public String[] atualiza5MelhoresClientes(List<Map.Entry<Cliente,Float>> ordenado){
        String[] melhoresCli=new String[5];
        Collections.reverse(ordenado);
        //PERCORRER A LIST
        return melhoresCli;
    }



    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorMenuPrincipal.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }

    public void clicaListaCompras(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaCompras.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }

    public void clicaListaEncomendas(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaEncomendas.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }

    public void clicaAtualizaStocks(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorAtualizaStocks.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }
}
