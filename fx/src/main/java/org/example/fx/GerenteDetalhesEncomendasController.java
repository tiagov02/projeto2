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
import org.controlsfx.control.PropertySheet;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.SingleInstance.EncUserTemp;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int idFatura= EncUserTemp.getInstance().getCurrentId();
        Fatura f= FaturaCRUD.findFatura(idFatura);
        Estado est= EstadoFaturaCRUD.getUltimoEstadoFatura(idFatura);
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
        estadoEnc.setText(est.getDescricao());
        moradaEnc.setText(morada);
        nomeCliente.setText(f.getClienteByIdcliente().getNome());
    }
    public void buttonBack(javafx.event.ActionEvent event) throws IOException {
        EncUserTemp.getInstance().setCurrentId(0);
        TrocaPaineis.changePanel(event, "GerenteListarEncomendas.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }
}
