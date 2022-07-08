package org.example.fx.ModelClasses;

public class EncomendaForLinhaModel {
    private Integer idProd;
    private String nomeProd;
    private int qtdProd;
    private float valTotal;

    public EncomendaForLinhaModel() {
    }

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public int getQtdProd() {
        return qtdProd;
    }

    public void setQtdProd(int qtdProd) {
        this.qtdProd = qtdProd;
    }

    public float getValTotal() {
        return valTotal;
    }

    public void setValTotal(float valTotal) {
        this.valTotal = valTotal;
    }
}
