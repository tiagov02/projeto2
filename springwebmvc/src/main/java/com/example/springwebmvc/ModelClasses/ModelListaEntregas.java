package com.example.springwebmvc.ModelClasses;

import java.util.ArrayList;
import java.util.List;

public class ModelListaEntregas {
    private String valor;
    private String descricao;

    public ModelListaEntregas(String valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
