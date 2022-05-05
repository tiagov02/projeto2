package com.example.bd.Entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@IdClass(LinhafaturaPK.class)
public class Linhafatura {
    private int numfatura;
    private int numproduto;
    private Integer quantidade;
    private BigDecimal preco;
    private Fatura faturaByNumfatura;
    private Produto produtoByNumproduto;

    @Id
    @Column(name = "numfatura", nullable = false)
    public int getNumfatura() {
        return numfatura;
    }

    public void setNumfatura(int numfatura) {
        this.numfatura = numfatura;
    }

    @Id
    @Column(name = "numproduto", nullable = false)
    public int getNumproduto() {
        return numproduto;
    }

    public void setNumproduto(int numproduto) {
        this.numproduto = numproduto;
    }

    @Basic
    @Column(name = "quantidade", nullable = true, precision = 0)
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Basic
    @Column(name = "preco", nullable = true, precision = 2)
    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Linhafatura that = (Linhafatura) o;

        if (numfatura != that.numfatura) return false;
        if (numproduto != that.numproduto) return false;
        if (quantidade != null ? !quantidade.equals(that.quantidade) : that.quantidade != null) return false;
        if (preco != null ? !preco.equals(that.preco) : that.preco != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numfatura;
        result = 31 * result + numproduto;
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        result = 31 * result + (preco != null ? preco.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "numfatura", referencedColumnName = "numfatura", nullable = false,insertable = false, updatable = false)
    public Fatura getFaturaByNumfatura() {
        return faturaByNumfatura;
    }

    public void setFaturaByNumfatura(Fatura faturaByNumfatura) {
        this.faturaByNumfatura = faturaByNumfatura;
    }

    @ManyToOne
    @JoinColumn(name = "numproduto", referencedColumnName = "numproduto", nullable = false,insertable = false, updatable = false)
    public Produto getProdutoByNumproduto() {
        return produtoByNumproduto;
    }

    public void setProdutoByNumproduto(Produto produtoByNumproduto) {
        this.produtoByNumproduto = produtoByNumproduto;
    }
}
