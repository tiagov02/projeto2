package org.example.fx;

import com.example.bd.CRUD.LinhaEncomendaFornecedorCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Linhaencomendafornecedor;
import com.example.bd.Entity.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.fx.ModelClasses.ListaComprasClass;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ColaboradorListaComprasController implements Initializable {
    @FXML
    private Button botaoAtualizaStocks;

    @FXML
    private Button botaoListaCompras;

    @FXML
    private Button botaoListarEncomendas;

    @FXML
    private Button botaoPaginaPrincipal;

    @FXML
    private TableView<ListaComprasClass> table_listaCompras;

    @FXML
    private TableColumn<ListaComprasClass, Date> tbl_dataAdd;

    @FXML
    private TableColumn<ListaComprasClass, Integer> tbl_id;

    @FXML
    private TableColumn<ListaComprasClass, String> tbl_produto;

    @FXML
    private TableColumn<ListaComprasClass, Integer> tbl_qtdComprar;

    @FXML
    private TableColumn<ListaComprasClass, Integer> tbl_qtdExistente;

    @FXML
    private TableColumn<ListaComprasClass, String> tbl_tipoProduto;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbl_dataAdd.setCellValueFactory(new PropertyValueFactory<>("dataAdd"));
        tbl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_produto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        tbl_qtdComprar.setCellValueFactory(new PropertyValueFactory<>("qtdComprar"));
        tbl_qtdExistente.setCellValueFactory(new PropertyValueFactory<>("qtdExistente"));
        tbl_tipoProduto.setCellValueFactory(new PropertyValueFactory<>("tipoProduto"));
        ListaComprasClass list= new ListaComprasClass();
        for(Linhaencomendafornecedor l: LinhaEncomendaFornecedorCRUD.findAllLinhasEncomendasFornecedores()){
            //list.setDataAdd(l.getEncomendafornecedorByNumencomenda());
            list.setId(l.getNumencomenda());
            list.setProduto(l.getProdutoByNumproduto().getNome());
            list.setQtdComprar(l.getQuantidade());
            list.setQtdExistente(l.getProdutoByNumproduto().getQuantidadestock());
            list.setTipoProduto(l.getProdutoByNumproduto().getTipoprodutoByIdtipoproduto().getSeccao());
            table_listaCompras.getItems().add(list);
        }
    }
}
