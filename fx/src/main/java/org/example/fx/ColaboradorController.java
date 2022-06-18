package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.CRUD.ProdutoCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Fatura;
import com.example.bd.Entity.Produto;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.security.KeyStore;
import java.util.*;

public class ColaboradorController implements Initializable{
    @FXML
    private Button botaoPaginaPrincipal;

    @FXML
    private Button botaoListaCompras;

    @FXML
    private Button botaoListarEncomendas;

    @FXML
    private Button botaoAtualizaStocks;

    @FXML
    private Text txt_acumuladoAnual;

    @FXML
    private Text txt_cli1;

    @FXML
    private Text txt_cli2;

    @FXML
    private Text txt_cli3;

    @FXML
    private Text txt_cli4;

    @FXML
    private Text txt_cli5;


    /**
     * Mudar para string
     */

    /*public void atualiza(javafx.event.ActionEvent event) throws IOException {
        TrocaPaineis.changePanel(event,"ColaboradorAtualizaStrocks","Atualização de stocks",ColaboradorController.class);

    }*/

    public Cliente[] listarCliMaisGasto(){
        Map<Cliente,Float> cliMap=guardaValorFaturasCli();
        List<Map.Entry<Cliente,Float>> ordenada=ordenaLista(cliMap);
        return atualiza5MelhoresClientes(ordenada);
    }

    public Map<Cliente,Float> guardaValorFaturasCli(){
        Map<Cliente,Float> melhoresCli=new HashMap<>();
        float valorGastoCli=0;
        //String[] melhoresCli=new String[5];
        for(Cliente cli: ClienteCRUD.findClientesTodos()){
            for(Fatura f: cli.getFaturasByIdcliente()){
                valorGastoCli+=f.getValorfatura().floatValue();
            }
            melhoresCli.put(cli,valorGastoCli);
            valorGastoCli=0;
        }
        return melhoresCli;
    }

    public List<Map.Entry<Cliente,Float>> ordenaLista(Map<Cliente,Float> cliValGasto){
        List<Map.Entry<Cliente,Float>> ordenado=new ArrayList<>(cliValGasto.entrySet());
        ordenado.sort(Map.Entry.comparingByValue());
        return ordenado;
    }

    public Cliente[] atualiza5MelhoresClientes(List<Map.Entry<Cliente,Float>> ordenado){
        int i=0;
        Cliente[] melhoresCli=new Cliente[5];
        Collections.reverse(ordenado);
        while(i!=4){
            for(Map.Entry<Cliente,Float> cli:ordenado){
                melhoresCli[i]= cli.getKey();
                i++;
            }
        }
        return melhoresCli;
    }


    public void clicaPaginaPrincipal(javafx.event.ActionEvent event) throws IOException{
        TrocaPaineis.changePanel(event, "ColaboradorMenuPrincipal.fxml", "Loja Produtos Biológicos", ColaboradorController.class);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Cliente[] clientes = listarCliMaisGasto();
        txt_cli1.setText(clientes[0].getNome());
        txt_cli2.setText(clientes[1].getNome());
        txt_cli3.setText(clientes[2].getNome());
        txt_cli4.setText(clientes[3].getNome());
        txt_cli5.setText(clientes[4].getNome());*/
        //Erro no Hibernate inicialize
    }
}
