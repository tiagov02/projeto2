package com.example.bd.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class LinhaencomendafornecedorPK implements Serializable {
    private int numproduto;
    private int numencomenda;

    @Column(name = "numproduto", nullable = false)
    @Id
    public int getNumproduto() {
        return numproduto;
    }

    public void setNumproduto(int numproduto) {
        this.numproduto = numproduto;
    }

    @Column(name = "numencomenda", nullable = false)
    @Id
    public int getNumencomenda() {
        return numencomenda;
    }

    public void setNumencomenda(int numencomenda) {
        this.numencomenda = numencomenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinhaencomendafornecedorPK that = (LinhaencomendafornecedorPK) o;

        if (numproduto != that.numproduto) return false;
        if (numencomenda != that.numencomenda) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numproduto;
        result = 31 * result + numencomenda;
        return result;
    }
}
