package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Colaborador;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.SingleInstance.EncUserTemp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GerenteAtualizaPwd implements Initializable {
    @FXML
    private PasswordField lb_pwd;

    @FXML
    private PasswordField lb_pwd1;

    @FXML
    private Label nomeUserLabel;
    @FXML
    private Button buttonalterar;
    @FXML
    private Button buttonlimpar;
    @FXML
    private Button buttonvoltar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Colaborador c= ColaboradorCRUD.findColaboradores(EncUserTemp.getInstance().getCurrentId());
        nomeUserLabel.setText(c.getUsername());
    }
    public void limparDados(){
        lb_pwd.setText("");
        lb_pwd1.setText("");
    }

    public void alterarPassword(){
        Colaborador c= ColaboradorCRUD.findColaboradores(EncUserTemp.getInstance().getCurrentId());
        String pwd="";
        if(lb_pwd.getText().equals(lb_pwd1.getText())){
            try{
               pwd = Encriptacao.encript(lb_pwd.getText());
            } catch (Exception e) {
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("ERRO!");
                dialogoAviso.setHeaderText("Não foi possível alterar pf tente mais tarde!!");
                dialogoAviso.showAndWait();
                return;
            }
            c.setPassword(pwd);
            try {
                ColaboradorCRUD.editColaborador(c);
            } catch (IdNaoEncontradoException e) {
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("ERRO!");
                dialogoAviso.setHeaderText("Não foi possível alterar pf tente mais tarde!!");
                dialogoAviso.showAndWait();
            }
            Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
            dialogoAviso.setTitle("SUCESSO!");
            dialogoAviso.setHeaderText("Alterou a password do colaborador: "+c.getUsername()+" com sucesso!");
            dialogoAviso.showAndWait();
        }else{
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!");
            dialogoAviso.setHeaderText("A password deverá ser igual nas duas labels");
            dialogoAviso.showAndWait();
        }
    }
    public void clicaLogout(javafx.event.ActionEvent event) throws IOException {
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
    public void clicaDefinicoesFornecedor(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteFornecedores.fxml","Listagem de encomendas",GerenteController.class);
    }
    public void clicaTipoProduto(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"TiposProduto.fxml","Loja Produtos Biológicos",GerenteController.class);
    }
}
