package com.example.bd.Entity;

import javax.persistence.*;
import java.util.Collection;

@NamedQuery(name="Fornecedores.FindByNome",query="select f from Fornecedor f WHERE f.nome=:nome")
@Entity
public class Fornecedor {
    private int idfornecedor;
    private String codpostal;
    private String nome;
    private String numcontabancaria;
    private String email;
    private String numtelefone;
    private String rua;
    private int numporta;
    private Collection<Encomendafornecedor> encomendafornecedorsByIdfornecedor;
    private Codpostais codpostaisByCodpostal;

    @Id
    @SequenceGenerator(name="fornecedor_idfornecedor_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="fornecedor_idfornecedor_seq")
    @Column(name = "idfornecedor", nullable = false)
    public int getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(int idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    @Basic
    @Column(name = "codpostal", nullable = false, length = 8)
    public String getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
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
    @Column(name = "numcontabancaria", nullable = true, length = 50)
    public String getNumcontabancaria() {
        return numcontabancaria;
    }

    public void setNumcontabancaria(String numcontabancaria) {
        this.numcontabancaria = numcontabancaria;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "numtelefone", nullable = true, length = 50)
    public String getNumtelefone() {
        return numtelefone;
    }

    public void setNumtelefone(String numtelefone) {
        this.numtelefone = numtelefone;
    }

    @Basic
    @Column(name = "rua", nullable = true, length = 50)
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    @Basic
    @Column(name = "numporta", nullable = false, precision = 0)
    public int getNumporta() {
        return numporta;
    }

    public void setNumporta(int numporta) {
        this.numporta = numporta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fornecedor that = (Fornecedor) o;

        if (idfornecedor != that.idfornecedor) return false;
        if (numporta != that.numporta) return false;
        if (codpostal != null ? !codpostal.equals(that.codpostal) : that.codpostal != null) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (numcontabancaria != null ? !numcontabancaria.equals(that.numcontabancaria) : that.numcontabancaria != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (numtelefone != null ? !numtelefone.equals(that.numtelefone) : that.numtelefone != null) return false;
        if (rua != null ? !rua.equals(that.rua) : that.rua != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idfornecedor;
        result = 31 * result + (codpostal != null ? codpostal.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (numcontabancaria != null ? numcontabancaria.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (numtelefone != null ? numtelefone.hashCode() : 0);
        result = 31 * result + (rua != null ? rua.hashCode() : 0);
        result = 31 * result + numporta;
        return result;
    }

    @OneToMany(mappedBy = "fornecedorByIdfornecedor")
    public Collection<Encomendafornecedor> getEncomendafornecedorsByIdfornecedor() {
        return encomendafornecedorsByIdfornecedor;
    }

    public void setEncomendafornecedorsByIdfornecedor(Collection<Encomendafornecedor> encomendafornecedorsByIdfornecedor) {
        this.encomendafornecedorsByIdfornecedor = encomendafornecedorsByIdfornecedor;
    }

    @ManyToOne
    @JoinColumn(name = "codpostal", referencedColumnName = "codpostal", nullable = false,insertable = false, updatable = false)
    public Codpostais getCodpostaisByCodpostal() {
        return codpostaisByCodpostal;
    }

    public void setCodpostaisByCodpostal(Codpostais codpostaisByCodpostal) {
        this.codpostaisByCodpostal = codpostaisByCodpostal;
    }
}
