package com.example.bd.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Cliente {
    private int idcliente;
    private int idtipocliente;
    private String codpostal;
    private String nome;
    private String telefone;
    private int numporta;
    private String rua;
    private String password;
    private String username;
    private Tipocliente tipoclienteByIdtipocliente;
    private Codpostais codpostaisByCodpostal;
    private Collection<Fatura> faturasByIdcliente;

    @Id
    @SequenceGenerator(name="cliente_idcliente_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cliente_idcliente_seq")
    @Column(name = "idcliente", nullable = false)
    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    @Basic
    @Column(name = "idtipocliente", nullable = false)
    public int getIdtipocliente() {
        return idtipocliente;
    }

    public void setIdtipocliente(int idtipocliente) {
        this.idtipocliente = idtipocliente;
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
    @Column(name = "telefone", nullable = true, length = 10)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Basic
    @Column(name = "numporta", nullable = false, precision = 0)
    public int getNumporta() {
        return numporta;
    }

    public void setNumporta(int numporta) {
        this.numporta = numporta;
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
    @Column(name = "password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        if (idcliente != cliente.idcliente) return false;
        if (idtipocliente != cliente.idtipocliente) return false;
        if (numporta != cliente.numporta) return false;
        if (codpostal != null ? !codpostal.equals(cliente.codpostal) : cliente.codpostal != null) return false;
        if (nome != null ? !nome.equals(cliente.nome) : cliente.nome != null) return false;
        if (telefone != null ? !telefone.equals(cliente.telefone) : cliente.telefone != null) return false;
        if (rua != null ? !rua.equals(cliente.rua) : cliente.rua != null) return false;
        if (password != null ? !password.equals(cliente.password) : cliente.password != null) return false;
        if (username != null ? !username.equals(cliente.username) : cliente.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcliente;
        result = 31 * result + idtipocliente;
        result = 31 * result + (codpostal != null ? codpostal.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        result = 31 * result + numporta;
        result = 31 * result + (rua != null ? rua.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idtipocliente", referencedColumnName = "idtipocliente", nullable = false,insertable = false, updatable = false)
    public Tipocliente getTipoclienteByIdtipocliente() {
        return tipoclienteByIdtipocliente;
    }

    public void setTipoclienteByIdtipocliente(Tipocliente tipoclienteByIdtipocliente) {
        this.tipoclienteByIdtipocliente = tipoclienteByIdtipocliente;
    }

    @ManyToOne
    @JoinColumn(name = "codpostal", referencedColumnName = "codpostal", nullable = false,insertable = false, updatable = false)
    public Codpostais getCodpostaisByCodpostal() {
        return codpostaisByCodpostal;
    }

    public void setCodpostaisByCodpostal(Codpostais codpostaisByCodpostal) {
        this.codpostaisByCodpostal = codpostaisByCodpostal;
    }

    @OneToMany(mappedBy = "clienteByIdcliente")
    public Collection<Fatura> getFaturasByIdcliente() {
        return faturasByIdcliente;
    }

    public void setFaturasByIdcliente(Collection<Fatura> faturasByIdcliente) {
        this.faturasByIdcliente = faturasByIdcliente;
    }
}
