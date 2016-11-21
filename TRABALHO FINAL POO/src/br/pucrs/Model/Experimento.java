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
        Mensagem mensagem;
        String texto = "Iniciando \n";
        for (int i = 0; i < tempoSimulacao ; i++) {
            if(this.arrayMensagens.size() > 0){
                for ( Mensagem msgAtual: this.arrayMensagens ) {
                    Antena antenaOrigem = msgAtual.getTelOrigem().getAntenaAssociada();
                    if(msgAtual.getStatus().equals(StatusMensagem.CELULAR_ORIGEM)) {
                        try {
                            antenaOrigem.enfileiraMensagem(msgAtual);
                            this.arrayMensagens.remove(msgAtual);
                            texto += "Mensagem: " + msgAtual.getNumeroMensagem() + " => antena: " + antenaOrigem.getIdent() + " -> enfileirando mensagem: De(" + msgAtual.getTelOrigem().getIdent() + "), Para (" + msgAtual.getTelDestino().getIdent() + ")\n";
                            break;
                        } catch (FilaCheiaException | StatusInvalidoException e) {
                            this.arrayMensagens.remove(msgAtual);
                            texto += e.getMessage() + "\n";
                            break;
                        }
                    }
                }
            }
            for ( Antena a : this.arrayAntenas){
                try{
                    mensagem = a.desenfileirarMensagem();
                    if(mensagem.getStatus().equals(StatusMensagem.ANTENA_PARA_CENTRAL)){
                        texto += "Mensagem: "+mensagem.getNumeroMensagem()+" => antena: "+a.getIdent()+" -> desenfileirando mensagem: De("+mensagem.getTelOrigem().getIdent()+"), Para ("+mensagem.getTelDestino().getIdent()+")\n";
                        this.central.empilhaProcesso(mensagem);
                        texto += "Mensagem: "+mensagem.getNumeroMensagem()+" => antena: "+a.getIdent()+" -> empilhando mensagem: De("+mensagem.getTelOrigem().getIdent()+"), Para ("+mensagem.getTelDestino().getIdent()+")\n";
                    } else if(mensagem.getStatus().equals(StatusMensagem.CELULAR_DESTINO)){
                        texto += "Mensagem: "+mensagem.getNumeroMensagem()+" => antena: "+ a.getIdent() +" -> entregue com sucesso: De("+mensagem.getTelOrigem().getIdent()+"), Para ("+mensagem.getTelDestino().getIdent()+")\n";
                    }
                } catch (FilaVaziaException| StatusInvalidoException e){
                    texto += e.getMessage() + "\n";
                }
            }
            try{
                mensagem = this.central.desempilhaProcesso();
                texto += "Mensagem: "+mensagem.getNumeroMensagem()+" => indo pra antena destino: "+mensagem.getTelDestino().getAntenaAssociada().getIdent()+" -> desempilhando mensagem: De("+mensagem.getTelOrigem().getIdent()+"), Para ("+mensagem.getTelDestino().getIdent()+")\n";
            } catch (SemProcessosException e){
                texto += e.getMessage() + "\n";
            }
        }

        //dados da simulação
        texto += "\n\nDados da simulação \n\n";
        for ( Antena a : this.arrayAntenas) {
            texto += "Antena: "+a.getIdent()+"\n\nMensagens Sucesso: "+a.getQntdMsgSucesso()+"\nMensagens Falhas: "+a.getQntdMsgFalhas()+"\n\n";
        }
        return texto;
    }

}
