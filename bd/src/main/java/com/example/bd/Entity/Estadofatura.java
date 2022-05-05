package com.example.bd.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@IdClass(EstadofaturaPK.class)
public class Estadofatura {
    private int numfatura;
    private int idestado;
    private Date datafatura;
    private Fatura faturaByNumfatura;
    private Estado estadoByIdestado;

    @Id
    @Column(name = "numfatura", nullable = false)
    public int getNumfatura() {
        return numfatura;
    }

    public void setNumfatura(int numfatura) {
        this.numfatura = numfatura;
    }

    @Id
    @Column(name = "idestado", nullable = false)
    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    @Basic
    @Column(name = "datafatura", nullable = true)
    public Date getDatafatura() {
        return datafatura;
    }

    public void setDatafatura(Date datafatura) {
        this.datafatura = datafatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estadofatura that = (Estadofatura) o;

        if (numfatura != that.numfatura) return false;
        if (idestado != that.idestado) return false;
        if (datafatura != null ? !datafatura.equals(that.datafatura) : that.datafatura != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numfatura;
        result = 31 * result + idestado;
        result = 31 * result + (datafatura != null ? datafatura.hashCode() : 0);
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
    @JoinColumn(name = "idestado", referencedColumnName = "idestado", nullable = false,insertable = false, updatable = false)
    public Estado getEstadoByIdestado() {
        return estadoByIdestado;
    }

    public void setEstadoByIdestado(Estado estadoByIdestado) {
        this.estadoByIdestado = estadoByIdestado;
    }
}
