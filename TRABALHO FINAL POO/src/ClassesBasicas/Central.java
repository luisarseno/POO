package ClassesBasicas;

public class Central {
	private String ident;
	private int quantDeProcessos;
	private Intervalo tempoPorAtendDosPrecessos;
	
	public Central(String ident, int quantDeProcessos, Intervalo tempoPorAtendDosPrecessos) {
		super();
		this.ident = ident;
		this.quantDeProcessos = quantDeProcessos;
		this.tempoPorAtendDosPrecessos = tempoPorAtendDosPrecessos;
	}
	
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public int getQuantDeProcessos() {
		return quantDeProcessos;
	}
	public void setQuantDeProcessos(int quantDeProcessos) {
		this.quantDeProcessos = quantDeProcessos;
	}
	public Intervalo getTempoPorAtendDosPrecessos() {
		return tempoPorAtendDosPrecessos;
	}
	public void setTempoPorAtendDosPrecessos(Intervalo tempoPorAtendDosPrecessos) {
		this.tempoPorAtendDosPrecessos = tempoPorAtendDosPrecessos;
	}

	
	
	
}
