package org.example.fx;

import com.example.bd.CRUD.*;
import com.example.bd.Entity.*;
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

public class ColaboradorController implements Initializable{
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
    private Text txt_acumulaGastos;

    @FXML
    private Text txt_lucro;

    private float vendas(){
        return EncomendaFornecedorCRUD.findGastos().floatValue();
    }
    private float gastos(){
        return EncomendaFornecedorCRUD.findGastos().floatValue();
    }

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorMenuPrincipal.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }

    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ConfirmacaoSaidaColaborador.fxml", "Loja Produtos Biológicos", GerenteController.class);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Cliente> clientes = ClienteCRUD.findMelhores();
        if(clientes.get(0) != null){
            txt_cli1.setText(clientes.get(0).getNome());
        }
        try{
            if(clientes.get(1) != null){
                txt_cli2.setText(clientes.get(1).getNome());
            }
        }
        catch (IndexOutOfBoundsException ex){
            txt_cli2.setText(null);
        }
        try{
            if(clientes.get(2) != null){
                txt_cli3.setText(clientes.get(2).getNome());
            }
        }
        catch (IndexOutOfBoundsException ex) {
            txt_cli3.setText(null);
        }
        try{
            if(clientes.get(3) != null){
                txt_cli4.setText(clientes.get(3).getNome());
            }
        }
        catch (IndexOutOfBoundsException ex) {
            txt_cli4.setText(null);
        }
        try{
            if(clientes.get(4) != null){
                txt_cli5.setText(clientes.get(4).getNome());
            }
        }
        catch (IndexOutOfBoundsException ex) {
            txt_cli5.setText(null);
        }
        txt_acumulaGastos.setText(Float.toString(gastos()));
        txt_acumuladoAnual.setText(Float.toString(vendas()));
        txt_lucro.setText(Float.toString(vendas()-gastos()));
    }
}
