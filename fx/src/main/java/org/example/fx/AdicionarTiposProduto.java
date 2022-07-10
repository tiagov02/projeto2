package org.example.fx;

import com.example.bd.CRUD.TipoProdutoCRUD;
import com.example.bd.Entity.Tipoproduto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.fx.Logica.TrocaPaineis;

import javax.persistence.RollbackException;
import java.io.IOException;

public class AdicionarTiposProduto {
    @FXML
    private TextField labelDescricao;

    public void addTipoProduto(javafx.event.ActionEvent event) throws IOException {
        if(labelDescricao.getText() != null){
            Tipoproduto tp=new Tipoproduto();
            tp.setSeccao(labelDescricao.getText());
            try{
                TipoProdutoCRUD.createTipoProduto(tp);
            }catch (RollbackException ex){
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("ERRO!!");
                dialogoAviso.setHeaderText("Não foi possível introduzir produto pf tente mais tarde!");
                dialogoAviso.showAndWait();
            }
        }
        Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
        dialogoAviso.setTitle("SUCESSO!!");
        dialogoAviso.setHeaderText("Introduziu um tipo de produto com sucesso!!!");
        dialogoAviso.showAndWait();
        back(event);
    }

    public void limpar(javafx.event.ActionEvent event){
        labelDescricao.setText(null);
    }

    public void back(javafx.event.ActionEvent event) throws IOException {
        clicaTipoProduto(event);
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
    public void clicaTipoProduto(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"TiposProduto.fxml","Loja Produtos Biológicos",GerenteController.class);
    }

}
