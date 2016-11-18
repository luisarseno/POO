package ClassesBasicas;

public class Telefone {
	private String ident;
	private String identDeAntenaAssociada;

	public Telefone(String ident, String identDeAntenaAssociada) {
		this.ident = ident;
		this.identDeAntenaAssociada = identDeAntenaAssociada;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getIdentDeAntenaAssociada() {
		return identDeAntenaAssociada;
	}

	public void setIdentDeAntenaAssociada(String identDeAntenaAssociada) {
		this.identDeAntenaAssociada = identDeAntenaAssociada;
	}
	
}
