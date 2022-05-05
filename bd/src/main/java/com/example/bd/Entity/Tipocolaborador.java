package com.example.bd.Entity;

import javax.persistence.*;

@Entity
public class Tipocolaborador {
    private int idtipo;
    private String descricao;

    @Id
    @SequenceGenerator(name="tipocolaborador_idtipo_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tipocolaborador_idtipo_seq")
    @Column(name = "idtipo", nullable = false)
    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

    @Basic
    @Column(name = "descricao", nullable = false, length = 20)
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

        Tipocolaborador that = (Tipocolaborador) o;

        if (idtipo != that.idtipo) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idtipo;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        return result;
    }
}
