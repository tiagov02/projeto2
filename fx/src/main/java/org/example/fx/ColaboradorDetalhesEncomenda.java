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

public class ColaboradorDetalhesEncomenda implements Initializable {
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
    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorMenuPrincipal.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
    }

    public void clicaLogout(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ConfirmacaoSaidaColaborador.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }

    public void clicaListaCompras(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaCompras.fxml", "Loja Produtos Biológicos", ColaboradorListaComprasController.class);
    }

    public void clicaListaEncomendas(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorListaEncomendas.fxml", "Loja Produtos Biológicos", ColaboradorListaEncomendasController.class);
    }

    public void clicaAtualizaStocks(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorAtualizaStocks.fxml", "Loja Produtos Biológicos", ColaboradorAtualizaStocksController.class);
    }
}
