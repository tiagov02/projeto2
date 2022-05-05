package com.example.bd.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Tipocliente {
    private int idtipocliente;
    private String tipocliente;
    private Collection<Cliente> clientesByIdtipocliente;

    @Id
    @SequenceGenerator(name="tipocliente_idtipocliente_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tipocliente_idtipocliente_seq")
    @Column(name = "idtipocliente", nullable = false)
    public int getIdtipocliente() {
        return idtipocliente;
    }

    public void setIdtipocliente(int idtipocliente) {
        this.idtipocliente = idtipocliente;
    }

    @Basic
    @Column(name = "tipocliente", nullable = true, length = 50)
    public String getTipocliente() {
        return tipocliente;
    }

    public void setTipocliente(String tipocliente) {
        this.tipocliente = tipocliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tipocliente that = (Tipocliente) o;

        if (idtipocliente != that.idtipocliente) return false;
        if (tipocliente != null ? !tipocliente.equals(that.tipocliente) : that.tipocliente != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtipocliente;
        result = 31 * result + (tipocliente != null ? tipocliente.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tipoclienteByIdtipocliente")
    public Collection<Cliente> getClientesByIdtipocliente() {
        return clientesByIdtipocliente;
    }

    public void setClientesByIdtipocliente(Collection<Cliente> clientesByIdtipocliente) {
        this.clientesByIdtipocliente = clientesByIdtipocliente;
    }
}
