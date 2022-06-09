package com.example.bd.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Produto {
    private int numproduto;
    private int idtipoproduto;
    private String nome;
    private Integer quantidademinima;
    private Integer quantidadestock;
    private BigDecimal valorunitario;
    private BigDecimal taxaiva;
    private BigDecimal valorunitariototal;
    private Collection<Linhaencomendafornecedor> linhaencomendafornecedorsByNumproduto;
    private Collection<Linhafatura> linhafaturasByNumproduto;
    private Tipoproduto tipoprodutoByIdtipoproduto;
    private String estado;

    @Id
    @SequenceGenerator(name="produto_numproduto_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="produto_numproduto_seq")
    @Column(name = "numproduto", nullable = false)
    public int getNumproduto() {
        return numproduto;
    }

    public void setNumproduto(int numproduto) {
        this.numproduto = numproduto;
    }

    @Basic
    @Column(name = "idtipoproduto", nullable = false)
    public int getIdtipoproduto() {
        return idtipoproduto;
    }

    public void setIdtipoproduto(int idtipoproduto) {
        this.idtipoproduto = idtipoproduto;
    }

    @Basic
    @Column(name = "nome", nullable = true, length = 50)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Basic
    @Column(name = "quantidademinima", nullable = true, precision = 0)
    public Integer getQuantidademinima() {
        return quantidademinima;
    }

    public void setQuantidademinima(Integer quantidademinima) {
        this.quantidademinima = quantidademinima;
    }

    @Basic
    @Column(name = "quantidadestock", nullable = true, precision = 0)
    public Integer getQuantidadestock() {
        return quantidadestock;
    }

    public void setQuantidadestock(Integer quantidadestock) {
        this.quantidadestock = quantidadestock;
    }

    @Basic
    @Column(name = "valorunitario", nullable = true, precision = 2)
    public BigDecimal getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(BigDecimal valorunitario) {
        this.valorunitario = valorunitario;
    }

    @Basic
    @Column(name = "taxaiva", nullable = false, precision = 3)
    public BigDecimal getTaxaiva() {
        return taxaiva;
    }

    public void setTaxaiva(BigDecimal taxaiva) {
        this.taxaiva = taxaiva;
    }

    @Basic
    @Column(name = "valorunitariototal", nullable = false, precision = 2)
    public BigDecimal getValorunitariototal() {
        return valorunitariototal;
    }

    public void setValorunitariototal(BigDecimal valorunitariototal) {
        this.valorunitariototal = valorunitariototal;
    }

    @Basic
    @Column(name = "estado", nullable = true, length = 10)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        if (numproduto != produto.numproduto) return false;
        if (idtipoproduto != produto.idtipoproduto) return false;
        if (nome != null ? !nome.equals(produto.nome) : produto.nome != null) return false;
        if (quantidademinima != null ? !quantidademinima.equals(produto.quantidademinima) : produto.quantidademinima != null)
            return false;
        if (quantidadestock != null ? !quantidadestock.equals(produto.quantidadestock) : produto.quantidadestock != null)
            return false;
        if (valorunitario != null ? !valorunitario.equals(produto.valorunitario) : produto.valorunitario != null)
            return false;
        if (taxaiva != null ? !taxaiva.equals(produto.taxaiva) : produto.taxaiva != null) return false;
        if (valorunitariototal != null ? !valorunitariototal.equals(produto.valorunitariototal) : produto.valorunitariototal != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numproduto;
        result = 31 * result + idtipoproduto;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (quantidademinima != null ? quantidademinima.hashCode() : 0);
        result = 31 * result + (quantidadestock != null ? quantidadestock.hashCode() : 0);
        result = 31 * result + (valorunitario != null ? valorunitario.hashCode() : 0);
        result = 31 * result + (taxaiva != null ? taxaiva.hashCode() : 0);
        result = 31 * result + (valorunitariototal != null ? valorunitariototal.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "produtoByNumproduto")
    public Collection<Linhaencomendafornecedor> getLinhaencomendafornecedorsByNumproduto() {
        return linhaencomendafornecedorsByNumproduto;
    }

    public void setLinhaencomendafornecedorsByNumproduto(Collection<Linhaencomendafornecedor> linhaencomendafornecedorsByNumproduto) {
        this.linhaencomendafornecedorsByNumproduto = linhaencomendafornecedorsByNumproduto;
    }

    @OneToMany(mappedBy = "produtoByNumproduto")
    public Collection<Linhafatura> getLinhafaturasByNumproduto() {
        return linhafaturasByNumproduto;
    }

    public void setLinhafaturasByNumproduto(Collection<Linhafatura> linhafaturasByNumproduto) {
        this.linhafaturasByNumproduto = linhafaturasByNumproduto;
    }

    @ManyToOne
    @JoinColumn(name = "idtipoproduto", referencedColumnName = "idtipoproduto", nullable = false,insertable = false, updatable = false)
    public Tipoproduto getTipoprodutoByIdtipoproduto() {
        return tipoprodutoByIdtipoproduto;
    }

    public void setTipoprodutoByIdtipoproduto(Tipoproduto tipoprodutoByIdtipoproduto) {
        this.tipoprodutoByIdtipoproduto = tipoprodutoByIdtipoproduto;
    }
}
