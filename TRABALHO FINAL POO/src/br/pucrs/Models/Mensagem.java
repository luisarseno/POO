package br.pucrs.Models;

/**
 * Created by Santana on 11/19/2016.
 */
public class Mensagem {
    private Telefone telOrigem;
    private Telefone telDestino;
    private String texto;
    private StatusMensagem status;

    public Mensagem(String texto, StatusMensagem status, Telefone origem, Telefone destino) {
        this.texto = texto;
        this.status = status;
        this.telOrigem = origem;
        this.telDestino = destino;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public StatusMensagem getStatus() {
        return status;
    }

    public void setStatus(StatusMensagem status) {
        this.status = status;
    }

    public Telefone getTelOrigem() {
        return telOrigem;
    }

    public Telefone getTelDestino() {
        return telDestino;
    }
}
