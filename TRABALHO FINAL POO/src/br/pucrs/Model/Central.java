package br.pucrs.Model;

import br.pucrs.Exceptions.FilaCheiaException;
import br.pucrs.Exceptions.SemProcessosException;
import br.pucrs.Exceptions.StatusInvalidoException;

import java.util.ArrayList;
import java.util.Stack;

public class Central {
	private String ident;
	private int quantDeProcessadores;
	private Intervalo tempoPorAtendDosPrecessos;
	private Stack<Mensagem> pilhaProcessos;
    private ArrayList<Telefone> celulares;

	public Central(String ident, int quantDeProcessos, Intervalo tempoPorAtendDosPrecessos) {
		this.ident = ident;
		this.quantDeProcessadores = quantDeProcessos;
		this.tempoPorAtendDosPrecessos = tempoPorAtendDosPrecessos;
        this.pilhaProcessos = new Stack<Mensagem>();
        this.celulares = new ArrayList<Telefone>();
	}
	
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public int getQuantDeProcessadores() {
		return quantDeProcessadores;
	}
	public void setQuantDeProcessadores(int quantDeProcessadores) {
		this.quantDeProcessadores = quantDeProcessadores;
	}
	public Intervalo getTempoPorAtendDosPrecessos() {
		return tempoPorAtendDosPrecessos;
	}
	public void setTempoPorAtendDosPrecessos(Intervalo tempoPorAtendDosPrecessos) {
		this.tempoPorAtendDosPrecessos = tempoPorAtendDosPrecessos;
	}

	public void addTelefone(Telefone telefone){
        this.celulares.add(telefone);
    }

    public boolean empilhaProcesso(Mensagem mensagem){
        this.pilhaProcessos.push(mensagem);
        return true;
    }

    public boolean desempilhaProcesso() throws SemProcessosException{
        if(this.pilhaProcessos.empty()){
            throw new SemProcessosException("A central não tem nenhum processo!");
        }
        Mensagem msgTmp = this.pilhaProcessos.pop();
        //em qual antena preciso enfileirar:
        Antena antenaTmp = msgTmp.getTelDestino().getAntenaAssociada();
        try{
            msgTmp.setStatus(StatusMensagem.CENTRAL_PARA_ANTENA);
            antenaTmp.enfileiraMensagem(msgTmp);
            return true;
        } catch (FilaCheiaException | StatusInvalidoException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
