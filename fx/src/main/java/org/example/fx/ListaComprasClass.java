package org.example.fx;

import javafx.collections.ObservableList;

import java.util.Date;

public class ListaComprasClass {
    private String tipoProduto;
    private String produto;
    private int qtdExistente;
    private int qtdComprar;
    private Date dataAdd;
    private int id;

    public ListaComprasClass(){
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQtdExistente() {
        return qtdExistente;
    }

    public void setQtdExistente(int qtdExistente) {
        this.qtdExistente = qtdExistente;
    }

    public int getQtdComprar() {
        return qtdComprar;
    }

    public void setQtdComprar(int qtdComprar) {
        this.qtdComprar = qtdComprar;
    }

    public Date getDataAdd() {
        return dataAdd;
    }

    public void setDataAdd(Date dataAdd) {
        this.dataAdd = dataAdd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}