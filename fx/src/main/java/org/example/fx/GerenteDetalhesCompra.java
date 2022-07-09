package org.example.fx;

import com.example.bd.CRUD.EncomendaFornecedorCRUD;
import com.example.bd.Entity.Encomendafornecedor;
import com.example.bd.Entity.Linhaencomendafornecedor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.fx.Logica.TrocaPaineis;
import org.example.fx.ModelClasses.ModelEncomendaFornecedor;
import org.example.fx.ModelClasses.ModelLinhaFatura;
import org.example.fx.SingleInstance.EncUserTemp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GerenteDetalhesCompra implements Initializable {
    @FXML
    private TableColumn<ModelLinhaFatura, Integer> colNumero;

    @FXML
    private TableColumn<ModelLinhaFatura, Float> colPreco;

    @FXML
    private TableColumn<ModelLinhaFatura, String> colProduto;

    @FXML
    private TableColumn<ModelLinhaFatura, Integer> colQtd;

    @FXML
    private TableColumn<ModelLinhaFatura, String> colTipoProduto;

    @FXML
    private Label moradaEnc;

    @FXML
    private Label nomeCliente;

    @FXML
    private TableView<ModelLinhaFatura> tableListaCompras;

    @FXML
    private Label telefonecliente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Encomendafornecedor ef= EncomendaFornecedorCRUD.findEncomendaFornecedor(EncUserTemp.getInstance().getCurrentId());
        String morada=ef.getFornecedorByIdfornecedor().getRua()+" , "+ef.getFornecedorByIdfornecedor().getNumporta()+" , "+
                ef.getFornecedorByIdfornecedor().getCodpostal()+" , "+
                ef.getFornecedorByIdfornecedor().getCodpostaisByCodpostal().getLocalidade();

        nomeCliente.setText(ef.getFornecedorByIdfornecedor().getNome());
        moradaEnc.setText(morada);
        telefonecliente.setText(ef.getFornecedorByIdfornecedor().getNumtelefone());

        for(Linhaencomendafornecedor lf: ef.getLinhaencomendafornecedorsByNumencomenda()){
            ModelLinhaFatura item= new ModelLinhaFatura();
            item.setIdProduto(lf.getNumproduto());
            item.setNomeProduto(lf.getProdutoByNumproduto().getNome());
            item.setTipoProduto(lf.getProdutoByNumproduto().getTipoprodutoByIdtipoproduto().getSeccao());
            item.setQtdComprada(lf.getQuantidade());
            item.setValTotal(lf.getValor().floatValue());
            tableListaCompras.getItems().add(item);
        }
    }
    public void buttonBack(javafx.event.ActionEvent event) throws IOException {
        EncUserTemp.getInstance().setCurrentId(0);
        TrocaPaineis.changePanel(event, "GerenteListaCompras.fxml", "Loja Produtos Biológicos", GerenteController.class);
    }
}
