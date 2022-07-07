package com.example.bd.Entity;

import javax.persistence.*;
import java.util.Collection;

@NamedQuery(name="Estado.FindPorPagar", query="select e from Estado e WHERE e.descricao='por pagar'")
@NamedQuery(name="Estado.FindByDescricao",query="select e from Estado e WHERE e.descricao=:descr")
@Entity
public class Estado {
    private int idestado;
    private String descricao;
    private Collection<Estadofatura> estadofaturasByIdestado;

    @Id
    @SequenceGenerator(name="estado_idestado_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="estado_idestado_seq")
    @Column(name = "idestado", nullable = false)
    public int getIdestado() {
        return idestado;
    }

    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    @Basic
    @Column(name = "descricao", nullable = true, length = 50)
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estado estado = (Estado) o;

        if (idestado != estado.idestado) return false;
        if (descricao != null ? !descricao.equals(estado.descricao) : estado.descricao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idestado;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "estadoByIdestado")
    public Collection<Estadofatura> getEstadofaturasByIdestado() {
        return estadofaturasByIdestado;
    }

    public void setEstadofaturasByIdestado(Collection<Estadofatura> estadofaturasByIdestado) {
        this.estadofaturasByIdestado = estadofaturasByIdestado;
    }
}
