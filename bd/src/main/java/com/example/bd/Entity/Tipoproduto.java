package com.example.bd.Entity;

import javax.persistence.*;
import java.util.Collection;

@NamedQuery(name = "TipoProduto.FindBySeccao",query="select tp from Tipoproduto tp WHERE tp.seccao=:seccao")
@Entity
public class Tipoproduto {
    private int idtipoproduto;
    private String seccao;
    private Collection<Produto> produtosByIdtipoproduto;

    @Id
    @SequenceGenerator(name="tipoproduto_idtipoproduto_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tipoproduto_idtipoproduto_seq")
    @Column(name = "idtipoproduto", nullable = false)
    public int getIdtipoproduto() {
        return idtipoproduto;
    }

    public void setIdtipoproduto(int idtipoproduto) {
        this.idtipoproduto = idtipoproduto;
    }

    @Basic
    @Column(name = "seccao", nullable = true, length = 50)
    public String getSeccao() {
        return seccao;
    }

    public void setSeccao(String seccao) {
        this.seccao = seccao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tipoproduto that = (Tipoproduto) o;

        if (idtipoproduto != that.idtipoproduto) return false;
        if (seccao != null ? !seccao.equals(that.seccao) : that.seccao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtipoproduto;
        result = 31 * result + (seccao != null ? seccao.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tipoprodutoByIdtipoproduto")
    public Collection<Produto> getProdutosByIdtipoproduto() {
        return produtosByIdtipoproduto;
    }

    public void setProdutosByIdtipoproduto(Collection<Produto> produtosByIdtipoproduto) {
        this.produtosByIdtipoproduto = produtosByIdtipoproduto;
    }
}
