package org.example.fx;

import com.example.bd.CRUD.EstadoFaturaCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.Entity.Estado;
import com.example.bd.Entity.Fatura;
import com.example.bd.Entity.Linhafatura;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.ModelClasses.ModelLinhaFatura;
import org.example.fx.SingleInstance.EncUserTemp;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GerenteDetalhesEncomendasController implements Initializable {

    @FXML
    private TableColumn<ModelLinhaFatura, Integer> colNumero;

    @FXML
    private TableColumn<ModelLinhaFatura, Float> colPreco;

    @FXML
    private TableColumn<ModelLinhaFatura, String> colProduto;

    @FXML
    private TableColumn<ModelLinhaFatura, String> colQtd;

    @FXML
    private TableColumn<ModelLinhaFatura, String> colTipoProduto;

    @FXML
    private TableView<ModelLinhaFatura> tableListaCompras;

    @FXML
    private Label estadoEnc;

    @FXML
    private Label moradaEnc;

    @FXML
    private Label nomeCliente;

    @FXML
    private Label telefonecliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int idFatura= EncUserTemp.getInstance().getCurrentId();
        Fatura f= FaturaCRUD.findFatura(idFatura);
        Estado est;
        try{
            est = EstadoFaturaCRUD.getUltimoEstadoFatura(idFatura);
            estadoEnc.setText(est.getDescricao());
        }catch(NoResultException ex){
            estadoEnc.setText("Erro! Não existe nenhum estado para esta fatura");
        }
        String morada= f.getMoradaentregaByIdentrega().getRua()+" , "+
                f.getMoradaentregaByIdentrega().getNumporta()+" , "+f.getMoradaentregaByIdentrega().getCodpostal()+" , "+
                f.getMoradaentregaByIdentrega().getCodpostaisByCodpostal().getLocalidade();
        colNumero.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("valTotal"));
        colProduto.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        colQtd.setCellValueFactory(new PropertyValueFactory<>("qtdComprada"));
        colTipoProduto.setCellValueFactory(new PropertyValueFactory<>("tipoProduto"));

        for(Linhafatura lf:f.getLinhafaturasByNumfatura()){
            ModelLinhaFatura ml=new ModelLinhaFatura();
            ml.setIdProduto(lf.getNumproduto());
            ml.setNomeProduto(lf.getProdutoByNumproduto().getNome());
            ml.setTipoProduto(lf.getProdutoByNumproduto().getTipoprodutoByIdtipoproduto().getSeccao());
            ml.setQtdComprada(lf.getQuantidade());
            ml.setValTotal(lf.getPreco().floatValue());
            tableListaCompras.getItems().add(ml);
        }
        moradaEnc.setText(morada);
        nomeCliente.setText(f.getClienteByIdcliente().getNome());
        telefonecliente.setText(f.getClienteByIdcliente().getNome());

    }
    public void buttonBack(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event, "GerenteListarEncomendas.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void ClickAddProduto(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event,"AdicionarProduto.fxml","Loja Produtos Biologicos",GerenteAdicionarProduto.class);
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
