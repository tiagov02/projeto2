package com.example.bd.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@NamedQuery(name="Faturas.Lucros", query="select SUM(f.valorfatura) from Fatura f")
@Entity
public class Fatura {
    private int numfatura;
    private int idcliente;
    private int idcolaborador;
    private int identrega;
    private Date data;
    private BigDecimal valorfatura;
    private Collection<Estadofatura> estadofaturasByNumfatura;
    private Cliente clienteByIdcliente;
    private Colaborador colaboradorByIdcolaborador;
    private Moradaentrega moradaentregaByIdentrega;
    private Collection<Linhafatura> linhafaturasByNumfatura;

    @Id
    @SequenceGenerator(name="fatura_numfatura_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="fatura_numfatura_seq")
    @Column(name = "numfatura", nullable = false)
    public int getNumfatura() {
        return numfatura;
    }

    public void setNumfatura(int numfatura) {
        this.numfatura = numfatura;
    }

    @Basic
    @Column(name = "idcliente", nullable = false)
    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    @Basic
    @Column(name = "idcolaborador", nullable = false)
    public int getIdcolaborador() {
        return idcolaborador;
    }

    public void setIdcolaborador(int idcolaborador) {
        this.idcolaborador = idcolaborador;
    }

    @Basic
    @Column(name = "identrega", nullable = false)
    public int getIdentrega() {
        return identrega;
    }

    public void setIdentrega(int identrega) {
        this.identrega = identrega;
    }

    @Basic
    @Column(name = "data", nullable = true)
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Basic
    @Column(name = "valorfatura", nullable = true, precision = 2)
    public BigDecimal getValorfatura() {
        return valorfatura;
    }

    public void setValorfatura(BigDecimal valorfatura) {
        this.valorfatura = valorfatura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fatura fatura = (Fatura) o;

        if (numfatura != fatura.numfatura) return false;
        if (idcliente != fatura.idcliente) return false;
        if (idcolaborador != fatura.idcolaborador) return false;
        if (identrega != fatura.identrega) return false;
        if (data != null ? !data.equals(fatura.data) : fatura.data != null) return false;
        if (valorfatura != null ? !valorfatura.equals(fatura.valorfatura) : fatura.valorfatura != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numfatura;
        result = 31 * result + idcliente;
        result = 31 * result + idcolaborador;
        result = 31 * result + identrega;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (valorfatura != null ? valorfatura.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "faturaByNumfatura")
    public Collection<Estadofatura> getEstadofaturasByNumfatura() {
        return estadofaturasByNumfatura;
    }

    public void setEstadofaturasByNumfatura(Collection<Estadofatura> estadofaturasByNumfatura) {
        this.estadofaturasByNumfatura = estadofaturasByNumfatura;
    }

    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente", nullable = false,insertable = false, updatable = false)
    public Cliente getClienteByIdcliente() {
        return clienteByIdcliente;
    }

    public void setClienteByIdcliente(Cliente clienteByIdcliente) {
        this.clienteByIdcliente = clienteByIdcliente;
    }

    @ManyToOne
    @JoinColumn(name = "idcolaborador", referencedColumnName = "idcolaborador", nullable = false,insertable = false, updatable = false)
    public Colaborador getColaboradorByIdcolaborador() {
        return colaboradorByIdcolaborador;
    }

    public void setColaboradorByIdcolaborador(Colaborador colaboradorByIdcolaborador) {
        this.colaboradorByIdcolaborador = colaboradorByIdcolaborador;
    }

    @ManyToOne
    @JoinColumn(name = "identrega", referencedColumnName = "identrega", nullable = false,insertable = false, updatable = false)
    public Moradaentrega getMoradaentregaByIdentrega() {
        return moradaentregaByIdentrega;
    }

    public void setMoradaentregaByIdentrega(Moradaentrega moradaentregaByIdentrega) {
        this.moradaentregaByIdentrega = moradaentregaByIdentrega;
    }

    @OneToMany(mappedBy = "faturaByNumfatura")
    public Collection<Linhafatura> getLinhafaturasByNumfatura() {
        return linhafaturasByNumfatura;
    }

    public void setLinhafaturasByNumfatura(Collection<Linhafatura> linhafaturasByNumfatura) {
        this.linhafaturasByNumfatura = linhafaturasByNumfatura;
    }
}
