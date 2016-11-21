package br.pucrs.Model;

import br.pucrs.Exceptions.*;

import java.io.File;
import java.util.ArrayList;

public class Experimento {
	private String nome;
    private File fileExecucao;
    private File fileSimulacao;
    private LeitorExecucao leitorExecucao;
    private LeitorSimulacao leitorSimulacao;
    private int tempoSimulacao;
    private Central central;
    private ArrayList<Antena> arrayAntenas;
    private ArrayList<Telefone> arrayTelefones;
    private ArrayList<Mensagem> arrayMensagens;

	public Experimento(File fileExecucao, File fileSimulacao) {
		this.fileExecucao = fileExecucao;
        this.fileSimulacao = fileSimulacao;
        this.leitorExecucao = new LeitorExecucao();

	}

	public String lerArquivoExecucao(){
        String texto = "";
        try {
            leitorExecucao.lerArquivo(fileExecucao);
            this.nome = leitorExecucao.getNomeExperimento();
            this.tempoSimulacao = leitorExecucao.getTempoSimulacao();
            this.central = leitorExecucao.getCentral();
            this.arrayAntenas = leitorExecucao.getArrayAntenas();
            texto += ("Nome:"+leitorExecucao.getNomeExperimento()+"\n"
                    + "Tempo: " +leitorExecucao.getTempoSimulacao()+"\n"
                    + "Central: "+leitorExecucao.getCentral()
            );


            for (Antena a: leitorExecucao.getArrayAntenas()) {
                texto +=(a);
            }
            this.arrayTelefones = leitorExecucao.getArrayTelefone();
            for (Telefone t : leitorExecucao.getArrayTelefone()) {
                texto +=(t);
            }
            this.central.setCelulares(this.arrayTelefones);
        } catch (LeitorException e){
            texto +=(e.getMessage());
        }
        return texto;
    }

    public String lerArquivoSimulacao(){
        this.leitorSimulacao = new LeitorSimulacao(this.arrayTelefones);
        String texto = "\n";
        try {
            leitorSimulacao.lerArquivo(fileSimulacao);
            this.arrayMensagens = leitorSimulacao.getArrayMensagem();
            for (Mensagem m : leitorSimulacao.getArrayMensagem()) {
                texto += (m);
            }
        } catch (LeitorException e){
            texto += (e.getMessage());
        }
        return texto;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public String fazSimulacao(){
        String texto = "";
        for (int i = 0; i < tempoSimulacao ; i++) {
            for ( Mensagem msgAtual: this.arrayMensagens ) {
                Antena antenaOrigem = msgAtual.getTelOrigem().getAntenaAssociada();
                Antena antenaDestino = msgAtual.getTelDestino().getAntenaAssociada();
                if(msgAtual.getStatus().equals("CELULAR_ORIGEM")){
                    try {
                        antenaOrigem.enfileiraMensagem(msgAtual);
                        texto += "Mensagem: "+msgAtual.getNumeroMensagem()+" => antena: "+antenaOrigem.getIdent()+" -> enfileirando mensagem: De("+msgAtual.getTelOrigem().getIdent()+"), Para ("+msgAtual.getTelDestino().getIdent()+")";
                    } catch (FilaCheiaException |StatusInvalidoException e){
                        texto += e.getMessage();
                    }
                } else if(msgAtual.getStatus().equals("ANTENA_PARA_CENTRAL")){
                    this.central.empilhaProcesso(msgAtual);
                    texto += "Mensagem: "+msgAtual.getNumeroMensagem()+" => central: "+this.central.getIdent()+" -> empilhando mensagem: De("+msgAtual.getTelOrigem().getIdent()+"), Para ("+msgAtual.getTelDestino().getIdent()+")";
                } else if (msgAtual.getStatus().equals("CENTRAL_PARA_ANTENA")){
                    try {
                        this.central.desempilhaProcesso();
                        texto += "Mensagem: "+msgAtual.getNumeroMensagem()+" => central: "+this.central.getIdent()+" -> desempilhando mensagem: De("+msgAtual.getTelOrigem().getIdent()+"), Para ("+msgAtual.getTelDestino().getIdent()+")";
                    } catch (SemProcessosException e ){
                        texto += e.getMessage();
                    }
                } else if(msgAtual.getStatus().equals("ANTENA_PARA_CELULAR")){


                } else if (msgAtual.getStatus().equals("CELULAR_DESTINO")){

                }
            }
        }
        return texto;
    }

}
