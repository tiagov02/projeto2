package org.example.fx;

public class ModelLinhaFatura {
    private int idProduto;
    private String nomeProduto;
    private int qtdComprada;
    private float valTotal;
    private String tipoProduto;

    public ModelLinhaFatura(){}
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQtdComprada() {
        return qtdComprada;
    }

    public void setQtdComprada(int qtdComprada) {
        this.qtdComprada = qtdComprada;
    }

    public float getValTotal() {
        return valTotal;
    }

    public void setValTotal(float valTotal) {
        this.valTotal = valTotal;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
}
