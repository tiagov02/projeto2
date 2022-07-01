package org.example.fx;

import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.CRUD.TipoClienteCRUD;
import com.example.bd.CRUD.TipoProdutoCRUD;
import com.example.bd.Entity.Produto;
import com.example.bd.Entity.Tipoproduto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class GerenteAdicionarProduto implements Initializable {
    @FXML
    private ComboBox<String> combotipoproduto;

    @FXML
    private TextField nomeproduto;

    @FXML
    private TextField qtdminima;

    @FXML
    private TextField qtdstock;

    @FXML
    private TextField taxaiva;

    @FXML
    private TextField valorunitario;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Tipoproduto tp: TipoProdutoCRUD.findTiposProduto()){
            combotipoproduto.getItems().add(tp.getSeccao());
        }
    }

    public void addProduto(javafx.event.ActionEvent event) throws IOException {
        boolean isWrong=false;
        Tipoproduto tp= TipoProdutoCRUD.findBySeccao(combotipoproduto.getValue());
        Produto p=new Produto();
        int quantMinima=0,quantStock=0;
        float vUnitTot=0;
        BigDecimal txiva=null;
        BigDecimal valunit=null;

        try{
            quantMinima=Integer.parseInt(qtdminima.getText());
            quantStock=Integer.parseInt(qtdstock.getText());
            txiva=new BigDecimal(taxaiva.getText());
            valunit=new BigDecimal(valorunitario.getText());
            isWrong=false;
        }
        catch(NumberFormatException ex){
            isWrong=true;
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!");
            dialogoAviso.setHeaderText("Erro! Não pode adicionar letras em campos numéricos");
            dialogoAviso.showAndWait();
        }
        if(!isWrong){
            if(txiva.floatValue()>1){
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("ERRO!");
                dialogoAviso.setHeaderText("Erro! A taxa de iva não pode ser maior que 1");
                dialogoAviso.showAndWait();
            }
            else{
                p.setNome(nomeproduto.getText());
                p.setQuantidademinima(quantMinima);
                p.setQuantidadestock(quantStock);
                p.setValorunitario(valunit);
                vUnitTot=valunit.floatValue()*(1+txiva.floatValue());
                BigDecimal valUnitTot=new BigDecimal(vUnitTot);
                p.setValorunitariototal(valUnitTot);
                p.setTaxaiva(txiva);
                p.setIdtipoproduto(tp.getIdtipoproduto());
                p.setEstado("ATIVO");
                ProdutoCRUD.createProduto(p);
                Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
                dialogoAviso.setTitle("SUCESSO!!!");
                dialogoAviso.setHeaderText("Introduziu um produto com sucesso!!");
                dialogoAviso.showAndWait();
                clicaPaginaPrincipal(event);
            }
        }
    }

    //FIZ ISTO MAS TAMBEM NÃO FUNCIONA...CHAMEI-A NO FX
    public void adicionarProduto(){
        float total = 0;
        Produto p = new Produto();
        p.setEstado("ATIVO");
        p.setNome(nomeproduto.getText());
        p.setIdtipoproduto(1);
        p.setQuantidademinima(1);
        p.setQuantidadestock(1);
        p.setValorunitario(new BigDecimal(12));
        p.setTaxaiva(new BigDecimal(0.23));
        p.setValorunitariototal(new BigDecimal(12));
        try {
            p.setQuantidademinima(Integer.parseInt(qtdminima.getText()));
        } catch (NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não pode introduzir letras na quantidade minima");
            dialogoAviso.showAndWait();
        }
        try {
            p.setQuantidadestock(Integer.parseInt(qtdstock.getText()));
        } catch (NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não pode introduzir letras na quantidade minima");
            dialogoAviso.showAndWait();
        }
        try {
            p.setValorunitario(new BigDecimal(valorunitario.getText()));
        } catch (NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não pode introduzir letras na quantidade minima");
            dialogoAviso.showAndWait();
        }
        try {
            p.setTaxaiva(new BigDecimal(taxaiva.getText()));
        } catch (NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não pode introduzir letras na quantidade minima");
            dialogoAviso.showAndWait();
        }
        total = (1+ p.getTaxaiva().floatValue());
        p.setValorunitariototal(new BigDecimal(total));
        for (Tipoproduto tp : TipoProdutoCRUD.findTiposProduto()){
            combotipoproduto.getItems().add(tp.getSeccao());
        }
        p.setIdtipoproduto(combotipoproduto.getSelectionModel().getSelectedIndex());
    }
    public void limpar(){
        nomeproduto.setText(null);
        qtdminima.setText(null);
        qtdstock.setText(null);
        taxaiva.setText(null);
        valorunitario.setText(null);
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

    public void clicaVoltar(javafx.event.ActionEvent event) throws IOException{
        clicaPaginaPrincipal(event);
    }
}
