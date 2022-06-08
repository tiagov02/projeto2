package org.example.fx;

import com.example.bd.CRUD.TipoClienteCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Tipocliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdicionarClienteController implements Initializable {

    @FXML
    private Button botaoAtualizaStocks;

    @FXML
    private Button botaoDefinicoesClientes;

    @FXML
    private Button botaoDefinicoesColaborador;

    @FXML
    private Button botaoListaCompras;

    @FXML
    private Button botaoListaEncomendas;

    @FXML
    private Button botaoPaginaPrincipal;

    @FXML
    private Button btn_Voltar;

    @FXML
    private TextField lbl_codPostal;

    @FXML
    private TextField lbl_localidade;

    @FXML
    private TextField lbl_nome;

    @FXML
    private TextField lbl_numPortoa;

    @FXML
    private TextField lbl_passwd;

    @FXML
    private TextField lbl_rua;

    @FXML
    private TextField lbl_telefone;

    @FXML
    private TextField lbl_username;

    @FXML
    private ComboBox<Tipocliente> select_tipoCliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        select_tipoCliente.getItems().addAll(TipoClienteCRUD.findTiposClientesTodos());
    }

    public void clickOK(javafx.event.ActionEvent event){

    }
}
