package com.example.bd.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Codpostais {
    private String codpostal;
    private String localidade;
    private Collection<Cliente> clientesByCodpostal;
    private Collection<Colaborador> colaboradorsByCodpostal;
    private Collection<Fornecedor> fornecedorsByCodpostal;
    private Collection<Moradaentrega> moradaentregasByCodpostal;

    @Id
    @Column(name = "codpostal", nullable = false, length = 8)
    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    @Basic
    @Column(name = "localidade", nullable = true, length = 50)
    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Codpostais that = (Codpostais) o;

        if (codpostal != null ? !codpostal.equals(that.codpostal) : that.codpostal != null) return false;
        if (localidade != null ? !localidade.equals(that.localidade) : that.localidade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codpostal != null ? codpostal.hashCode() : 0;
        result = 31 * result + (localidade != null ? localidade.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "codpostaisByCodpostal")
    public Collection<Cliente> getClientesByCodpostal() {
        return clientesByCodpostal;
    }

    public void setClientesByCodpostal(Collection<Cliente> clientesByCodpostal) {
        this.clientesByCodpostal = clientesByCodpostal;
    }

    @OneToMany(mappedBy = "codpostaisByCodpostal")
    public Collection<Colaborador> getColaboradorsByCodpostal() {
        return colaboradorsByCodpostal;
    }

    public void setColaboradorsByCodpostal(Collection<Colaborador> colaboradorsByCodpostal) {
        this.colaboradorsByCodpostal = colaboradorsByCodpostal;
    }

    @OneToMany(mappedBy = "codpostaisByCodpostal")
    public Collection<Fornecedor> getFornecedorsByCodpostal() {
        return fornecedorsByCodpostal;
    }

    public void setFornecedorsByCodpostal(Collection<Fornecedor> fornecedorsByCodpostal) {
        this.fornecedorsByCodpostal = fornecedorsByCodpostal;
    }

    @OneToMany(mappedBy = "codpostaisByCodpostal")
    public Collection<Moradaentrega> getMoradaentregasByCodpostal() {
        return moradaentregasByCodpostal;
    }

    public void setMoradaentregasByCodpostal(Collection<Moradaentrega> moradaentregasByCodpostal) {
        this.moradaentregasByCodpostal = moradaentregasByCodpostal;
    }
}
