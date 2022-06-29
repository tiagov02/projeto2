package com.example.bd.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
@NamedQuery(name="EncomendaFornecedor.SearchProduto"
        ,query="select lef from Linhaencomendafornecedor lef WHERE lef.produtoByNumproduto.nome like CONCAT('%',:nome,'%')")
@NamedQuery(name="EncomendaFornecedor.SearchIdProd",
query="select lef from Linhaencomendafornecedor lef WHERE lef.produtoByNumproduto.numproduto=:num")

@Entity
@IdClass(LinhaencomendafornecedorPK.class)
public class Linhaencomendafornecedor {
    private int numproduto;
    private int numencomenda;
    private BigDecimal valor;
    private Integer quantidade;
    private Produto produtoByNumproduto;
    private Encomendafornecedor encomendafornecedorByNumencomenda;

    @Id
    @Column(name = "numproduto", nullable = false)
    public int getNumproduto() {
        return numproduto;
    }

    public void setNumproduto(int numproduto) {
        this.numproduto = numproduto;
    }

    @Id
    @Column(name = "numencomenda", nullable = false)
    public int getNumencomenda() {
        return numencomenda;
    }

    public void setNumencomenda(int numencomenda) {
        this.numencomenda = numencomenda;
    }

    @Basic
    @Column(name = "valor", nullable = true, precision = 2)
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Basic
    @Column(name = "quantidade", nullable = true, precision = 0)
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Linhaencomendafornecedor that = (Linhaencomendafornecedor) o;

        if (numproduto != that.numproduto) return false;
        if (numencomenda != that.numencomenda) return false;
        if (valor != null ? !valor.equals(that.valor) : that.valor != null) return false;
        if (quantidade != null ? !quantidade.equals(that.quantidade) : that.quantidade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numproduto;
        result = 31 * result + numencomenda;
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "numproduto", referencedColumnName = "numproduto", nullable = false,insertable = false, updatable = false)
    public Produto getProdutoByNumproduto() {
        return produtoByNumproduto;
    }

    public void setProdutoByNumproduto(Produto produtoByNumproduto) {
        this.produtoByNumproduto = produtoByNumproduto;
    }

    @ManyToOne
    @JoinColumn(name = "numencomenda", referencedColumnName = "numencomenda", nullable = false,insertable = false, updatable = false)
    public Encomendafornecedor getEncomendafornecedorByNumencomenda() {
        return encomendafornecedorByNumencomenda;
    }

    public void setEncomendafornecedorByNumencomenda(Encomendafornecedor encomendafornecedorByNumencomenda) {
        this.encomendafornecedorByNumencomenda = encomendafornecedorByNumencomenda;
    }
}
