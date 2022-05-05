package com.example.bd.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Encomendafornecedor {
    private int numencomenda;
    private int idfornecedor;
    private BigDecimal valortotal;
    private Integer quantidade;
    private Fornecedor fornecedorByIdfornecedor;
    private Collection<Linhaencomendafornecedor> linhaencomendafornecedorsByNumencomenda;

    @Id
    @SequenceGenerator(name="encomendafornecedor_numencomenda_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="encomendafornecedor_numencomenda_seq")
    @Column(name = "numencomenda", nullable = false)
    public int getNumencomenda() {
        return numencomenda;
    }

    public void setNumencomenda(int numencomenda) {
        this.numencomenda = numencomenda;
    }

    @Basic
    @Column(name = "idfornecedor", nullable = false)
    public int getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(int idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    @Basic
    @Column(name = "valortotal", nullable = true, precision = 2)
    public BigDecimal getValortotal() {
        return valortotal;
    }

    public void setValortotal(BigDecimal valortotal) {
        this.valortotal = valortotal;
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

        Encomendafornecedor that = (Encomendafornecedor) o;

        if (numencomenda != that.numencomenda) return false;
        if (idfornecedor != that.idfornecedor) return false;
        if (valortotal != null ? !valortotal.equals(that.valortotal) : that.valortotal != null) return false;
        if (quantidade != null ? !quantidade.equals(that.quantidade) : that.quantidade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numencomenda;
        result = 31 * result + idfornecedor;
        result = 31 * result + (valortotal != null ? valortotal.hashCode() : 0);
        result = 31 * result + (quantidade != null ? quantidade.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idfornecedor", referencedColumnName = "idfornecedor", nullable = false,insertable = false, updatable = false)
    public Fornecedor getFornecedorByIdfornecedor() {
        return fornecedorByIdfornecedor;
    }

    public void setFornecedorByIdfornecedor(Fornecedor fornecedorByIdfornecedor) {
        this.fornecedorByIdfornecedor = fornecedorByIdfornecedor;
    }

    @OneToMany(mappedBy = "encomendafornecedorByNumencomenda")
    public Collection<Linhaencomendafornecedor> getLinhaencomendafornecedorsByNumencomenda() {
        return linhaencomendafornecedorsByNumencomenda;
    }

    public void setLinhaencomendafornecedorsByNumencomenda(Collection<Linhaencomendafornecedor> linhaencomendafornecedorsByNumencomenda) {
        this.linhaencomendafornecedorsByNumencomenda = linhaencomendafornecedorsByNumencomenda;
    }
}
