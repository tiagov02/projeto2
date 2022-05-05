package com.example.bd.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class LinhafaturaPK implements Serializable {
    private int numfatura;
    private int numproduto;

    @Column(name = "numfatura", nullable = false)
    @Id
    public int getNumfatura() {
        return numfatura;
    }

    public void setNumfatura(int numfatura) {
        this.numfatura = numfatura;
    }

    @Column(name = "numproduto", nullable = false)
    @Id
    public int getNumproduto() {
        return numproduto;
    }

    public void setNumproduto(int numproduto) {
        this.numproduto = numproduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinhafaturaPK that = (LinhafaturaPK) o;

        if (numfatura != that.numfatura) return false;
        if (numproduto != that.numproduto) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numfatura;
        result = 31 * result + numproduto;
        return result;
    }
}
