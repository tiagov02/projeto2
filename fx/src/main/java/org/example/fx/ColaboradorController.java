package org.example.fx;

import com.example.bd.CRUD.ClienteCRUD;
import com.example.bd.CRUD.FaturaCRUD;
import com.example.bd.Entity.Cliente;
import com.example.bd.Entity.Fatura;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.example.fx.Logica.TrocaPaineis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColaboradorController {
    @FXML
    private Button btn_atualizaStocks;

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


    public void atualiza(javafx.event.ActionEvent event) {
    }

    public String[] atualiza5MelhoresClientes(){
        String[] melhoresCli=new String[5];
        for(Cliente cli: ClienteCRUD.findClientesTodos()){
            for(Fatura f: cli.getFaturasByIdcliente()){
//inacabada
            }
        }
        return melhoresCli;
    }
}
