package br.pucrs.Models;

import br.pucrs.Exceptions.FilaCheiaException;
import br.pucrs.Exceptions.FilaVaziaException;
import br.pucrs.Exceptions.StatusInvalidoException;

import java.util.Queue;

public class Antena {
	private String ident;
	private int capacidadeDaFila;
	private Intervalo intervalo;
    private Queue<Mensagem> filaDeMensagens;
	private int qntdMsgFalhas;
	private int qntdMsgSucesso;

	public Antena(String ident, int capacidadeDaFila, Intervalo intervalo) {;
		this.ident = ident;
		setCapacidadeDaFila(capacidadeDaFila);
		this.intervalo = intervalo;
		this.qntdMsgFalhas = 0;
		this.qntdMsgSucesso = 0;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public int getCapacidadeDaFila() {
		return capacidadeDaFila;
	}

	public void setCapacidadeDaFila(int capacidadeDaFila) {
		if(capacidadeDaFila >= 0)this.capacidadeDaFila = capacidadeDaFila;
		else this.capacidadeDaFila = 0;
	}
	public int getQntdMsgFalhas() {
		return qntdMsgFalhas;
	}

	public int getQntdMsgSucesso() {
		return qntdMsgSucesso;
	}

	public boolean enfileiraMensagem(Mensagem mensagem) throws FilaCheiaException,StatusInvalidoException {
		if(this.filaDeMensagens.size() > this.capacidadeDaFila){
			this.qntdMsgFalhas++;
			throw new FilaCheiaException("A fila está cheia");
		}
		if(mensagem.getStatus().equals(StatusMensagem.CELULAR)){
            //mensagem vindo do celular para a antena,tem que ir para central
			mensagem.setStatus(StatusMensagem.ANTENA_PARA_CENTRAL);
		} else if (mensagem.getStatus().equals(StatusMensagem.CENTRAL_PARA_ANTENA)){
            //mensagem vindo da central para a antena, tem que ir para o celular
			mensagem.setStatus(StatusMensagem.ANTENA_PARA_CELULAR);
		} else {
			//status invalido da mensagem
			throw new StatusInvalidoException("Mensagem com status inválido");
		}
		this.filaDeMensagens.add(mensagem);
		return true;
	}

	public Mensagem desenfileirarMensagem() throws FilaVaziaException, StatusInvalidoException{
		if(this.filaDeMensagens.size() == 0 ){
			throw new FilaVaziaException("A fila está vazia");
		}
		Mensagem msgTmp = this.filaDeMensagens.remove();
		if(msgTmp.getStatus().equals(StatusMensagem.ANTENA_PARA_CELULAR)){
            //se a mensagem vai pro celular e com sucesso
			msgTmp.setStatus(StatusMensagem.CELULAR);
			this.qntdMsgSucesso++;
			return msgTmp;
		} else if(msgTmp.getStatus().equals(StatusMensagem.ANTENA_PARA_CENTRAL)){
            //mensagem está indo para a central
			msgTmp.setStatus(StatusMensagem.CENTRAL_PARA_ANTENA);
			return msgTmp;
		} else {
			//nao pode entrar aqui
			throw new StatusInvalidoException("Mensagem com status inválido");
		}
	}


	/**Somente para testar os bgl
	public String toString() {
		String txt = "";
		txt = ident + "\n" + capacidadeDaFila + "\n" + intervalo;
		return txt;
	}
	**/

}
