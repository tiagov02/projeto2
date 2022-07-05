package com.example.bd.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
@NamedQuery(name="MoradaEntrega.GetMoradaLoja",query="select mor from Moradaentrega mor WHERE mor.rua='LOJA'")
@Entity
public class Moradaentrega {
    private int identrega;
    private String codpostal;
    private Date horaentrega;
    private Date dataentrega;
    private String rua;
    private Integer numporta;
    private Collection<Fatura> faturasByIdentrega;
    private Codpostais codpostaisByCodpostal;

    @Id
    @SequenceGenerator(name="moradaentrega_identrega_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="moradaentrega_identrega_seq")
    @Column(name = "identrega", nullable = false)
    public int getIdentrega() {
        return identrega;
    }

    public void setIdentrega(int identrega) {
        this.identrega = identrega;
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
    @Column(name = "horaentrega", nullable = true)
    public Date getHoraentrega() {
        return horaentrega;
    }

    public void setHoraentrega(Date horaentrega) {
        this.horaentrega = horaentrega;
    }

    @Basic
    @Column(name = "dataentrega", nullable = true)
    public Date getDataentrega() {
        return dataentrega;
    }

    public void setDataentrega(Date dataentrega) {
        this.dataentrega = dataentrega;
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
    @Column(name = "numporta", nullable = true, precision = 0)
    public Integer getNumporta() {
        return numporta;
    }

    public void setNumporta(Integer numporta) {
        this.numporta = numporta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moradaentrega that = (Moradaentrega) o;

        if (identrega != that.identrega) return false;
        if (codpostal != null ? !codpostal.equals(that.codpostal) : that.codpostal != null) return false;
        if (horaentrega != null ? !horaentrega.equals(that.horaentrega) : that.horaentrega != null) return false;
        if (dataentrega != null ? !dataentrega.equals(that.dataentrega) : that.dataentrega != null) return false;
        if (rua != null ? !rua.equals(that.rua) : that.rua != null) return false;
        if (numporta != null ? !numporta.equals(that.numporta) : that.numporta != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = identrega;
        result = 31 * result + (codpostal != null ? codpostal.hashCode() : 0);
        result = 31 * result + (horaentrega != null ? horaentrega.hashCode() : 0);
        result = 31 * result + (dataentrega != null ? dataentrega.hashCode() : 0);
        result = 31 * result + (rua != null ? rua.hashCode() : 0);
        result = 31 * result + (numporta != null ? numporta.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "moradaentregaByIdentrega")
    public Collection<Fatura> getFaturasByIdentrega() {
        return faturasByIdentrega;
    }

    public void setFaturasByIdentrega(Collection<Fatura> faturasByIdentrega) {
        this.faturasByIdentrega = faturasByIdentrega;
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
