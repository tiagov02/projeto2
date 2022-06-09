package org.example.fx.Logica;

import com.example.bd.Entity.Cliente;
import org.example.fx.ClienteTipo;
import org.example.fx.Exceptions.NaoExistenteException;

import java.util.ArrayList;
import java.util.List;

public class MetodosPesquisa {

     private static ClienteTipo pesquisaBin(ArrayList<ClienteTipo> cli, String nomePesq) throws NaoExistenteException{
        int meio=0, ini=0, fim=cli.size()-1;
        while (ini <= fim) {
            meio=(fim+ini)/2;
            if (nomePesq.equals(cli.get(meio).getNomeCliente())) {
                return cli.get(meio);
            }
            if (nomePesq.compareTo(cli.get(meio).getNomeCliente())<0) {
                fim=meio-1;
            }
            else {
                ini=meio+1;
            }
        }
        throw new NaoExistenteException("O cliente que pesquisou não existe!!");
    }
}
