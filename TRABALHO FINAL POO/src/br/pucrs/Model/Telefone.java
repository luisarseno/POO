package br.pucrs.Model;

public class Telefone {
	private String ident;
	private Antena antenaAssociada;

	public Telefone(String ident, Antena antena) {
		this.ident = ident;
		this.antenaAssociada = antena;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public Antena getAntenaAssociada() {
		return antenaAssociada;
	}

	public void setAntenaAssociada(Antena antena) {
		this.antenaAssociada = antena;
	}
	
}
