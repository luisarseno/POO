package br.pucrs.Model;

/**
 * Created by Santana on 11/19/2016.
 */
public class Mensagem {
    private Telefone telOrigem;
    private Telefone telDestino;
    private int numeroMensagem;
    private StatusMensagem status;

    public Mensagem(int numeroMensagem, StatusMensagem status, Telefone origem, Telefone destino) {
        this.numeroMensagem = numeroMensagem;
        this.status = status;
        this.telOrigem = origem;
        this.telDestino = destino;
    }

    public int getNumeroMensagem() {
        return numeroMensagem;
    }

    public void setNumeroMensagem(int numeroMensagem) {
        this.numeroMensagem = numeroMensagem;
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

    @Override
    public String toString() {
        return "Mensagem{" +
                "telOrigem=" + telOrigem +
                ", telDestino=" + telDestino +
                ", texto='" + numeroMensagem + '\'' +
                ", status=" + status +
                '}';
    }
}
