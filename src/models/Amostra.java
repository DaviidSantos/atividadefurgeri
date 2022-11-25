package models;

import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;

public class Amostra implements Serializable {
    private static final Long serialVersionUID = 1L;
    private int numAmostra;
    private String nomeAmostra;
    private int numSolicitacaoDeAnalise;
    private String statusAmostra;
    private Date dataDeEntrada;
    private String tipo;
    private String notaFiscal;
    private Date validade;

    public Amostra() {
    }

    public Amostra(int numAmostra, String statusAmostra){
        this.numAmostra = numAmostra;
        this.statusAmostra = statusAmostra;
    }

    public Amostra(String nomeAmostra, int numSolicitacaoDeAnalise, String statusAmostra, Date dataDeEntrada,String tipo, String notaFiscal, Date validade) {
        this.nomeAmostra = nomeAmostra;
        this.numSolicitacaoDeAnalise = numSolicitacaoDeAnalise;
        this.statusAmostra = statusAmostra;
        this.dataDeEntrada = dataDeEntrada;
        this.tipo = tipo;
        this.notaFiscal = notaFiscal;
        this.validade = validade;
    }

    public int getNumAmostra() {
        return numAmostra;
    }

    public void setNumAmostra(int numAmostra) {
        this.numAmostra = numAmostra;
    }

    public String getNomeAmostra() {
        return nomeAmostra;
    }

    public void setNomeAmostra(String nomeAmostra) {
        this.nomeAmostra = nomeAmostra;
    }

    public int getNumSolicitacaoDeAnalise() {
        return numSolicitacaoDeAnalise;
    }

    public void setNumSolicitacaoDeAnalise(int numSolicitacaoDeAnalise) {
        this.numSolicitacaoDeAnalise = numSolicitacaoDeAnalise;
    }

    public String getStatusAmostra() {
        return statusAmostra;
    }

    public void setStatusAmostra(String statusAmostra) {
        this.statusAmostra = statusAmostra;
    }

    public Date getDataDeEntrada() {
        return dataDeEntrada;
    }

    public void setDataDeEntrada(Date dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    @Override
    public String toString() {
        return "Amostra{" +
                "numAmostra='" + numAmostra + '\'' +
                ", nomeAmostra='" + nomeAmostra + '\'' +
                ", numSolicitacaoDeAnalise='" + numSolicitacaoDeAnalise + '\'' +
                ", statusAmostra=" + statusAmostra +
                ", dataDeEntrada=" + dataDeEntrada +
                ", tipo='" + tipo + '\'' +
                ", notaFiscal='" + notaFiscal + '\'' +
                ", validade=" + validade +
                '}';
    }
}
