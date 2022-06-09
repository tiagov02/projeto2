package org.example.fx;

import com.example.bd.CRUD.exceptions.IdNaoEncontradoException;
import com.example.bd.Entity.Produto;

import javax.persistence.EntityManager;

import static com.example.bd.CRUD.ProdutoCRUD.findProduto;
import static com.example.bd.CRUD.ProdutoCRUD.getEntityManager;

public class ProdutoTipo {
    private String nome;
    private String tipoProduto;
    private int id;
     private int qtdStock;
     private int qtdMinima;
     private float valUnit;

    public ProdutoTipo() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdStock() {
        return qtdStock;
    }

    public void setQtdStock(int qtdStock) {
        this.qtdStock = qtdStock;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public float getValUnit() {
        return valUnit;
    }

    public void setValUnit(float valUnit) {
        this.valUnit = valUnit;
    }
}
