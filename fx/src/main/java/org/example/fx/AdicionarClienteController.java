package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.CodPostaisCRUD;
import com.example.bd.CRUD.TipoClienteCRUD;
import com.example.bd.Encrypt.Encriptacao;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Codpostais;
import com.example.bd.Entity.Tipocliente;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
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
    private ComboBox<String> select_tipoCliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Tipocliente tp:TipoClienteCRUD.findTiposClientesTodos()){
            select_tipoCliente.getItems().add(tp.getTipocliente());
        }
    }

    public void clickOK(javafx.event.ActionEvent event) throws IOException {
        int nPorta=0;
        Cliente cli=new Cliente();
        int tp=getIdtipocliente(select_tipoCliente.getValue());
        cli.setUsername(lbl_username.getText());
        try {
            cli.setPassword(Encriptacao.encript(lbl_passwd.getText()));
        }
        catch(Exception e){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não foi possivel concluir o pedido pf tente mais tarde!");
            dialogoAviso.showAndWait();
        }
        cli.setNome(lbl_nome.getText());
        try {
            cli.setTelefone(lbl_telefone.getText());
            if (cli.getTelefone().length() > 9){
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("ERRO!!");
                dialogoAviso.setHeaderText("Não pode introduzir mais que nove numeros no numero de telemovel!!");
                dialogoAviso.showAndWait();
            }
        } catch (NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não pode introduzir letras no numero de telefone");
            dialogoAviso.showAndWait();
        }

        cli.setIdtipocliente(tp);

        if(CodPostaisCRUD.findCodPostal(lbl_codPostal.getText())==null){
            Codpostais cod=new Codpostais();
            cod.setCodpostal(lbl_codPostal.getText());
            cod.setLocalidade(lbl_localidade.getText());
            CodPostaisCRUD.create(cod);
        }
        cli.setCodpostal(lbl_codPostal.getText());
        if (lbl_codPostal.getText().equals(CodPostaisCRUD.findCodPostal(lbl_codPostal.getText()))){
            lbl_localidade.getText().equals(CodPostaisCRUD.findCodPostal(lbl_codPostal.getText()).getLocalidade());
        }
        try{
            nPorta=Integer.parseInt(lbl_numPortoa.getText());
        }
        catch(NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Deverá introduzir apenas números no campo de número de Porta! Obrigado!");
            dialogoAviso.showAndWait();
        }
        cli.setNumporta(nPorta);
        cli.setRua(lbl_rua.getText());
        ClienteCRUD.createCliente(cli);
        Alert dialogoAviso = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoAviso.setTitle("SUCESSO!!!");
        dialogoAviso.setHeaderText("INTRODUZIU UM CLIENTE COM SUCESSO");
        dialogoAviso.showAndWait();
        TrocaPaineis.changePanel(event,"GerentedefinicoesCliente.fxml","Loja Produtos Biológicos",GerenteListaCliente.class);
    }

    private int getIdtipocliente(String tipoCli){
        for(Tipocliente tp:TipoClienteCRUD.findTiposClientesTodos()){
            if(tipoCli.equals(tp.getTipocliente())){
                return tp.getIdtipocliente();
            }
        }
        return -1;
    }

    public void clearButton(){
        lbl_nome.setText("");
        lbl_telefone.setText("");
        lbl_codPostal.setText("");
        lbl_localidade.setText("");
        lbl_rua.setText("");
        lbl_numPortoa.setText("");
        lbl_username.setText("");
        lbl_passwd.setText("");
    }

    public void clickBack(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "GerentedefinicoesClientes.fxml", "Loja Produtos Biológicos", GerenteController.class);
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
