package ClassesBasicas;

public class Eventos {
	private String IdentOrigem;
	private String IdentDestino;
	
	public Eventos(String identOrigem, String identDestino) {
		super();
		IdentOrigem = identOrigem;
		IdentDestino = identDestino;
	}

	public String getIdentOrigem() {
		return IdentOrigem;
	}

	public void setIdentOrigem(String identOrigem) {
		IdentOrigem = identOrigem;
	}

	public String getIdentDestino() {
		return IdentDestino;
	}

	public void setIdentDestino(String identDestino) {
		IdentDestino = identDestino;
	}
	
	
}
