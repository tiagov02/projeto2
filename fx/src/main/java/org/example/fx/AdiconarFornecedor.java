package org.example.fx;

import com.example.bd.CRUD.CodPostaisCRUD;
import com.example.bd.CRUD.FornecedorCRUD;
import com.example.bd.Entity.Codpostais;
import com.example.bd.Entity.Colaborador;
import com.example.bd.Entity.Fornecedor;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;

public class AdiconarFornecedor {
    @FXML
    private TextField nomeforn;
    @FXML
    private TextField telefone;
    @FXML
    private TextField email;
    @FXML
    private TextField iban;
    @FXML
    private TextField rua;
    @FXML
    private TextField numporta;
    @FXML
    private TextField codpostal;
    @FXML
    private TextField localidade;


    public void limpar(){
        nomeforn.setText("");
        telefone.setText("");
        email.setText("");
        iban.setText("");
        rua.setText("");
        numporta.setText("");
        codpostal.setText("");
        localidade.setText("");
    }

    public void adicionarFornecedor(javafx.event.ActionEvent event) throws IOException{
        Fornecedor f = new Fornecedor();
        f.setNome(nomeforn.getText());
        try {
            f.setNumtelefone(telefone.getText());
            if (f.getNumtelefone().length() > 9){
                Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
                dialogoAviso.setTitle("ERRO!!");
                dialogoAviso.setHeaderText("Não pode introduzir mais que nove numeros no numero de telemovel!!");
                dialogoAviso.showAndWait();
            }
            } catch (NumberFormatException e){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não pode introduzir letras no numero de telefone");
            dialogoAviso.showAndWait();
        }
        f.setEmail(email.getText());
        f.setNumcontabancaria(iban.getText());
        f.setRua(rua.getText());
        try {
            f.setNumporta(Integer.parseInt(numporta.getText()));
        } catch (NumberFormatException ex){
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("ERRO!!");
            dialogoAviso.setHeaderText("Não pode introduzir letras no numero de telefone");
            dialogoAviso.showAndWait();
        }
        if(CodPostaisCRUD.findCodPostal(codpostal.getText())==null){
            Codpostais cod = new Codpostais();
            cod.setCodpostal(codpostal.getText());
            cod.setLocalidade(localidade.getText());
            CodPostaisCRUD.create(cod);
        }
        f.setCodpostal(codpostal.getText());
        if (codpostal.getText().equals(CodPostaisCRUD.findCodPostal(codpostal.getText()))){
            localidade.getText().equals(CodPostaisCRUD.findCodPostal(codpostal.getText()).getLocalidade());
        }
        FornecedorCRUD.createFornecedor(f);
        Alert dialogoAviso = new Alert(Alert.AlertType.INFORMATION);
        dialogoAviso.setTitle("SUCESSO!!");
        dialogoAviso.setHeaderText("Introduziu um fornecedor com sucesso.");
        dialogoAviso.showAndWait();
        TrocaPaineis.changePanel(event, "GerenteFornecedores.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }


    public void clicaVoltar(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "GerenteFornecedores.fxml", "Loja Produtos Biológicos", GerenteController.class);
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
    public void clicaDefinicoesFornecedor(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"GerenteFornecedores.fxml","Listagem de encomendas",GerenteController.class);
    }
    public void clicaTipoProduto(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"TiposProduto.fxml","Loja Produtos Biológicos",GerenteController.class);
    }

    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ConfirmacaoSaida.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }
}
