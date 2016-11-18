package ClassesBasicas;

public class Antena {
	private String ident;
	private int capacidadeDaFila;
	private Intervalo intervalo;
	
	public Antena(String ident, int capacidadeDaFila, Intervalo intervalo) {;
		this.ident = ident;
		setCapacidadeDaFila(capacidadeDaFila);
		this.intervalo = intervalo;
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


	/**Somente para testar os bgl
	public String toString() {
		String txt = "";
		txt = ident + "\n" + capacidadeDaFila + "\n" + intervalo;
		return txt;
	}
	**/

}
