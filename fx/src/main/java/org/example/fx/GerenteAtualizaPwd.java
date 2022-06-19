package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Colaborador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;

public class GerenteAtualizaPwd {
    @FXML
    private PasswordField lb_pwd;
    @FXML
    private TextField lb_user;
    @FXML
    private PasswordField lb_pwd1;
    @FXML
    private Button buttonalterar;
    @FXML
    private Button buttonlimpar;
    @FXML
    private Button buttonvoltar;

    public void limparDados(){
        lb_pwd.setText("");
        lb_pwd1.setText("");
        lb_user.setText("");
    }

    public void alterarPassword(){
        int cont=0;
        Colaborador col=null;
        for (Colaborador c : ColaboradorCRUD.findTodosColaboradores()){
            if(this.lb_pwd.getText().equals(c.getUsername())){
                cont++;
                col=c;
            }
        }
        if(cont==0){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!");
            dialogoAviso.setHeaderText("Esse user não existe!!");
            dialogoAviso.showAndWait();
        }
        try {
            col.setPassword(Encriptacao.encript(lb_pwd.getText()));
        }
        catch (Exception ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!");
            dialogoAviso.setHeaderText("Erro no sistema!!Pf tente mais tarde ou contacte os Serviços");
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
}
