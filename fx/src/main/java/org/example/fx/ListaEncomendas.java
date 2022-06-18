package org.example.fx;

public class ListaEncomendas {
    private int numFatura;
    private int idCliente;
    private String nomeCliente;
    private String morada;
    private String telefoneCliente;
    private float valorTotal;
    private String estadoFatura;

    public ListaEncomendas() {
    }

    public int getNumFatura() {
        return numFatura;
    }

    public void setNumFatura(int numFatura) {
        this.numFatura = numFatura;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEstadoFatura() {
        return estadoFatura;
    }

    public void setEstadoFatura(String estadoFatura) {
        this.estadoFatura = estadoFatura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
