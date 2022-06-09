package com.example.bd.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
public class Colaborador {
    private int idcolaborador;
    private String codpostal;
    private String nome;
    private String telefone;
    private BigDecimal salario;
    private int numporta;
    private String rua;
    private int idtipo;
    private String password;
    private String username;
    private Codpostais codpostaisByCodpostal;
    private Collection<Fatura> faturasByIdcolaborador;
    private String estado;

    @Id
    @SequenceGenerator(name="colaborador_idcolaborador_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="colaborador_idcolaborador_seq")
    @Column(name = "idcolaborador", nullable = false)
    public int getIdcolaborador() {
        return idcolaborador;
    }

    public void setIdcolaborador(int idcolaborador) {
        this.idcolaborador = idcolaborador;
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
    @Column(name = "telefone", nullable = false, length = 10)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Basic
    @Column(name = "salario", nullable = false, precision = 2)
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
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
    @Column(name = "idtipo", nullable = false)
    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
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

    @Basic
    @Column(name = "estado", nullable = true, length = 10)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Colaborador that = (Colaborador) o;

        if (idcolaborador != that.idcolaborador) return false;
        if (numporta != that.numporta) return false;
        if (idtipo != that.idtipo) return false;
        if (codpostal != null ? !codpostal.equals(that.codpostal) : that.codpostal != null) return false;
        if (nome != null ? !nome.equals(that.nome) : that.nome != null) return false;
        if (telefone != null ? !telefone.equals(that.telefone) : that.telefone != null) return false;
        if (salario != null ? !salario.equals(that.salario) : that.salario != null) return false;
        if (rua != null ? !rua.equals(that.rua) : that.rua != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcolaborador;
        result = 31 * result + (codpostal != null ? codpostal.hashCode() : 0);
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        result = 31 * result + (salario != null ? salario.hashCode() : 0);
        result = 31 * result + numporta;
        result = 31 * result + (rua != null ? rua.hashCode() : 0);
        result = 31 * result + idtipo;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "codpostal", referencedColumnName = "codpostal", nullable = false,insertable = false, updatable = false)
    public Codpostais getCodpostaisByCodpostal() {
        return codpostaisByCodpostal;
    }

    public void setCodpostaisByCodpostal(Codpostais codpostaisByCodpostal) {
        this.codpostaisByCodpostal = codpostaisByCodpostal;
    }

    @OneToMany(mappedBy = "colaboradorByIdcolaborador")
    public Collection<Fatura> getFaturasByIdcolaborador() {
        return faturasByIdcolaborador;
    }

    public void setFaturasByIdcolaborador(Collection<Fatura> faturasByIdcolaborador) {
        this.faturasByIdcolaborador = faturasByIdcolaborador;
    }
}
