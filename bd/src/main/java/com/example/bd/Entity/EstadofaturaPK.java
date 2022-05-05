package com.example.bd.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class EstadofaturaPK implements Serializable {
    private int numfatura;
    private int idestado;

    @Column(name = "numfatura", nullable = false)
    @Id
    public int getNumfatura() {
        return numfatura;
    }

    public void setNumfatura(int numfatura) {
        this.numfatura = numfatura;
    }

    @Column(name = "idestado", nullable = false)
    @Id
    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadofaturaPK that = (EstadofaturaPK) o;

        if (numfatura != that.numfatura) return false;
        if (idestado != that.idestado) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numfatura;
        result = 31 * result + idestado;
        return result;
    }
}
