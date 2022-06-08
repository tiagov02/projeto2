package org.example.fx;

import com.example.bd.CRUD.ColaboradorCRUD;
import com.example.bd.CRUD.TipoColaboradorCRUD;
import com.example.bd.Entity.Colaborador;
import com.example.bd.Entity.Tipocolaborador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerenteDefinicoesColaborador implements Initializable {

    @FXML
    private TableColumn<Colaborador, Integer> colNumero;
    @FXML
    private TableColumn<Colaborador, String> colNome;
    @FXML
    private TableColumn<Colaborador, String> colTelefone;
    @FXML
    private TableColumn<Colaborador, Float> colsalario;
    @FXML
    private TableColumn<Colaborador, Integer> colEstado;

    public TableView<Colaborador> tableColaborador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int aux=1;
       for (Colaborador colab: ColaboradorCRUD.findTodosColaboradores()){
               colNumero.setCellValueFactory(new PropertyValueFactory<>("idcolaborador"));
               colNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
               colTelefone.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
               colsalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
               tableColaborador.setItems(getColaborador());
       }
    }

    public ObservableList<Colaborador> getColaborador(){
        ObservableList<Colaborador> colaboradors = FXCollections.observableArrayList();
        List<Colaborador> colaboradorList = ColaboradorCRUD.findTodosColaboradores();
        colaboradors.addAll(colaboradorList);

        return colaboradors;
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
