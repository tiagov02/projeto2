package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.CodPostaisCRUD;
import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Codpostais;
import com.example.bd.Entity.Colaborador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.converter.BigDecimalStringConverter;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;

public class AdicionarColaborador {
    @FXML
    private TextField nomecolab;
    @FXML
    private TextField telefone;
    @FXML
    private TextField salario;
    @FXML
    private TextField numporta;
    @FXML
    private TextField rua;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField codpostal;
    @FXML
    private TextField localidade;


    public void limpar(){
        nomecolab.setText("");
        telefone.setText("");
        salario.setText("");
        numporta.setText("");
        rua.setText("");
        username.setText("");
        password.setText("");
        codpostal.setText("");
        localidade.setText("");
    }

    public void adicionarColaborador(javafx.event.ActionEvent event) throws IOException {
        Colaborador c1 = new Colaborador();
        c1.setIdtipo(1);
        c1.setNome(nomecolab.getText());
        c1.setTelefone(telefone.getText());
        c1.setSalario(new BigDecimal(salario.getText()));
        try{
            c1.setNumporta(Integer.parseInt(numporta.getText()));
        }catch(NumberFormatException exception){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não pode introduzir letras no numero da porta!!");
            dialogoAviso.showAndWait();
        }
        c1.setRua(rua.getText());
        c1.setUsername(username.getText());
        try {
            c1.setPassword(Encriptacao.encript(password.getText()));
        } catch (Exception ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não pode introduzir uma password vazia.");
            dialogoAviso.showAndWait();
        }
        if(CodPostaisCRUD.findCodPostal(codpostal.getText())==null){
            Codpostais cod = new Codpostais();
            cod.setCodpostal(codpostal.getText());
            cod.setLocalidade(localidade.getText());
            CodPostaisCRUD.create(cod);
        }
        c1.setCodpostal(codpostal.getText());
        ColaboradorCRUD.createColaborador(c1);
        TrocaPaineis.changePanel(event,"GerentedefinicoesColaborador.fxml","Loja Prdutos Biológicos",GerenteDefinicoesColaborador.class);

    }


    public void clicaVoltar(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "GerentedefinicoesColaborador.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException {
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
        TrocaPaineis.changePanel(event,"GerenteListarEncomendas.fxml","Listagem de encomendas",GerenteController.class);
    }
}
